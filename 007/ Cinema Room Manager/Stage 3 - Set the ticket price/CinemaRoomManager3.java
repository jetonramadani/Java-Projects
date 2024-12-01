import java.util.Scanner;

class CinemaRoom3 {
    private int rows;
    private int seats;
    private boolean[][] chairs;
    private final Scanner sc;
    private int total;
    private int half;

    public CinemaRoom3(Scanner sc) {
        this.sc = sc;
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        total = rows * seats;
        chairs = new boolean[rows][seats];
        half = rows / 2;
        print();
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
                System.out.print(chairs[i][j] ? "B " : "S ");
            }
            System.out.println();
        }
    }

    public void arrangeChair() {
        System.out.println("Enter a row number:");
        int r = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int s = sc.nextInt();
        chairs[r - 1][s - 1] = true;
        System.out.println(chairs[r - 1][s - 1]);
        printPrice(r);
    }

    private int getPrice(int row) {
        if (total < 60) {
            return 10;
        } else {
            return row <= half ? 10 : 8;
        }
    }

    public void printPrice(int row) {
        System.out.println("Ticket price: $" + getPrice(row));
    }
}

public class CinemaRoomManager3 {
    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        CinemaRoom3 cinemaRoom = new CinemaRoom3(sc);
        cinemaRoom.arrangeChair();
        cinemaRoom.print();
    }
}