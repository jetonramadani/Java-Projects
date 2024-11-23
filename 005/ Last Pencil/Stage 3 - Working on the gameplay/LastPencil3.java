import java.util.Scanner;

public class LastPencil3 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PencilGame pencilGame = initializePencilGame(sc);
            pencilGame.startGame(sc);
        }
    }

    private static PencilGame initializePencilGame(Scanner sc) {
        System.out.println("How many pencils would you like to use:");
        int numberOfPencils = Integer.parseInt(sc.nextLine());
        System.out.println("Who will be the first (John, Jack):");
        String playerName = sc.nextLine();

        return new PencilGame(numberOfPencils, playerName);
    }
}


class PencilGame {
    private int numberOfPencils;
    private final String player1;
    private final String player2;
    private Boolean isPlayer1;

    public PencilGame(int numberOfPencils, String player1) {
        this.numberOfPencils = numberOfPencils;
        this.player1 = player1;
        this.player2 = player1.equals("John") ? "Jack" : "John";
        this.isPlayer1 = true;
    }

    private void nextMove(Scanner sc) {
        System.out.println("|".repeat(this.numberOfPencils));
        System.out.printf("%s's turn:%n",
                this.isPlayer1 ? this.player1 : this.player2);

        int numberOfPencils = Integer.parseInt(sc.nextLine());
        this.numberOfPencils -= numberOfPencils;
        this.isPlayer1 = !this.isPlayer1;
    }

    private boolean isGameEnd() {
        return this.numberOfPencils <= 0;
    }

    public void startGame(Scanner sc) {
        while (!this.isGameEnd()) {
            this.nextMove(sc);
        }
    }
}