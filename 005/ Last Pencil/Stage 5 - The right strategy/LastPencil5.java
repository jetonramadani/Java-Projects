import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LastPencil5 {
    public static final Random random = new Random();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PencilGame5 pencilGame = initializePencilGame(sc);
            pencilGame.startGame(sc);
        }
    }

    private static PencilGame5 initializePencilGame(Scanner sc) {
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

        return new PencilGame5(numberOfPencils, playerName);
    }
}


class PencilGame5 {
    private int numberOfPencils;
    private final String player1;
    private final String player2;
    private Boolean isPlayer1;

    public PencilGame5(int numberOfPencils, String player1) {
        this.numberOfPencils = numberOfPencils;
        this.player1 = "John";
        this.player2 = "Jack";
        this.isPlayer1 = player1.equals("John");
    }

    private int botMove() {
        int move;
        if (numberOfPencils == 1) {
            move = 1;
        } else {
            move = switch (numberOfPencils % 4) {
                case 0 -> 3;
                case 3 -> 2;
                case 2 -> 1;
                default -> LastPencil5.random.nextInt(3) + 1;
            };
        }
        System.out.println(move);
        return move;
    }

    private void nextMove(Scanner sc) {
        System.out.println("|".repeat(this.numberOfPencils));
        System.out.printf("%s's turn!%n",
                this.isPlayer1 ? this.player1 : this.player2);

        int numberOfPencils;
        while (true) {
            try {
                numberOfPencils = this.isPlayer1 ? Integer.parseInt(sc.nextLine()) : this.botMove();
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
            System.out.printf("%s won!%n", this.isPlayer1 ? player1 : player2);
        }
        return isGameEnd;
    }

    public void startGame(Scanner sc) {
        while (!this.isGameEnd()) {
            this.nextMove(sc);
        }
    }
}