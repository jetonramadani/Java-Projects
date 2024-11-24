import java.util.Scanner;

public class LastPencil2 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("How many pencils would you like to use:");
            int numberOfPencils = Integer.parseInt(sc.nextLine());
            System.out.println("Who will be the first (John, Jack):");
            String playerName = sc.nextLine();
            System.out.println("|".repeat(numberOfPencils));
            System.out.printf("%s is going first!%n", playerName);
        }
    }
}
