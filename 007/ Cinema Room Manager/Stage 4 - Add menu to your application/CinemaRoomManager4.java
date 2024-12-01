package cinema;

import java.util.Scanner;
class CinemaRoom4 {
    private final int rows;
    private final int seats;
    private final boolean[][] chairs;
    private final Scanner sc;
    private final int total;
    private final int half;
    public CinemaRoom4(Scanner sc) {
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
    private void print() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(chairs[i][j] ? "B " : "S ");
            }
            System.out.println();
        }
    }
    private void arrangeChair() {
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
    private void printPrice(int row) {
        System.out.println("Ticket price: $" + getPrice(row));
    }
    public void run() {
        int selection = -1;
        do {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "0. Exit");
            selection = sc.nextInt();
            switch (selection) {
                case 1:
                    print();
                    break;
                case 2:
                    arrangeChair();
                    break;
                default:
                    break;
            }
        } while (selection > 0 && selection <= 2);
    }
}
public class CinemaRoomManager4 {


    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        CinemaRoom4 cinemaRoom = new CinemaRoom4(sc);
        cinemaRoom.run();
    }
}