import java.util.Scanner;
class CinemaRoom1 {
    private int rows;
    private int seats;
    private int[][] chairs;
    private final Scanner sc;
    private  int total;
    public CinemaRoom1(Scanner sc) {
        this.sc = sc;
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        total = rows * seats;
    }
    public void print() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= chairs[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < chairs.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < chairs[i].length; j++) {
                System.out.print("S ");
            }
            System.out.println();
        }
    }
    private int getPrice() {
        if (total < 60) {
            return total * 10;
        } else {
            int half = rows / 2;
            return half * seats * 10 + (rows - half) * seats * 8;
        }
    }
    public void printPrice() {
        System.out.println("Total income: ");
        System.out.println("$" + getPrice());
    }
}
public class CinemaRoomManager2 {

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        CinemaRoom1 cinemaRoom = new CinemaRoom1(sc);
        cinemaRoom.printPrice();
    }
}