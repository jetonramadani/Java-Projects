import java.util.Scanner;

import java.util.List;

public class LastPencil4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PencilGame4 pencilGame = initializePencilGame(sc);
            pencilGame.startGame(sc);
        }
    }

    private static PencilGame4 initializePencilGame(Scanner sc) {
        System.out.println("How many pencils would you like to use:");
        int numberOfPencils;
        while (true) {
            try {
                numberOfPencils = Integer.parseInt(sc.nextLine());
                if (numberOfPencils < 0) {
                    throw new Exception();
                } else if (numberOfPencils == 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("The number of pencils should be numeric");
            }

        }
        System.out.println("Who will be the first (John, Jack):");
        String playerName;
        while (true) {
            playerName = sc.nextLine();
            if (List.of("John", "Jack").contains(playerName)) {
                break;
            }
            System.out.println("Choose between John and Jack");
        }

        return new PencilGame4(numberOfPencils, playerName);
    }
}


class PencilGame4 {
    private int numberOfPencils;
    private final String player1;
    private final String player2;
    private Boolean isPlayer1;

    public PencilGame4(int numberOfPencils, String player1) {
        this.numberOfPencils = numberOfPencils;
        this.player1 = player1;
        this.player2 = player1.equals("John") ? "Jack" : "John";
        this.isPlayer1 = true;
    }

    private void nextMove(Scanner sc) {
        System.out.println("|".repeat(this.numberOfPencils));
        System.out.printf("%s's turn!%n",
                this.isPlayer1 ? this.player1 : this.player2);

        int numberOfPencils;
        while (true) {
            try {
                numberOfPencils = Integer.parseInt(sc.nextLine());
                if (!List.of(1, 2, 3).contains(numberOfPencils)) {
                    throw new Exception();
                } else if (numberOfPencils > this.numberOfPencils) {
                    System.out.println("Too many pencils were taken");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
        this.numberOfPencils -= numberOfPencils;
        this.isPlayer1 = !this.isPlayer1;
    }

    private boolean isGameEnd() {
        boolean isGameEnd = this.numberOfPencils == 0;
        if (isGameEnd) {
            System.out.printf("%s won!", this.isPlayer1 ? player1 : player2);
        }
        return isGameEnd;
    }

    public void startGame(Scanner sc) {
        while (!this.isGameEnd()) {
            this.nextMove(sc);
        }
    }
}