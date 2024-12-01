import java.util.Scanner;

public class TicTacToeSimple2 {
    public static void printBoard(char[] plays) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(plays[i * 3 + j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        System.out.print("Enter cells: ");
        char[] plays = sc.next().toCharArray();
        printBoard(plays);
    }
}