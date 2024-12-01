import java.util.Scanner;
class CinemaRoom5 {
    private final int rows;
    private final int seats;
    private final boolean[][] chairs;
    private final Scanner sc;
    private final int total;
    private final int half;
    private int sold;
    private int currentIncome;
    private final int totalIncome;
    public CinemaRoom5(Scanner sc) {
        this.sc = sc;
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        total = rows * seats;
        chairs = new boolean[rows][seats];
        half = rows / 2;
        print();
        sold = currentIncome = 0;
        if (total < 60) {
            totalIncome = total * 10;
        } else {
            totalIncome = half * seats * 10 + (rows - half) * seats * 8;
        }
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
        while(true) {
            System.out.println("Enter a row number:");
            int r = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int s = sc.nextInt();
            try {
                if (!chairs[r - 1][s - 1]) {
                    chairs[r - 1][s - 1] = true;
                    ++sold;
                    printPrice(r);
                    break;
                } else {
                    System.out.println("That ticket has already been purchased");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }
    private int getPrice(int row) {
        if (total < 60) {
            return 10;
        } else {
            return row <= half ? 10 : 8;
        }
    }
    private void printPrice(int row) {
        int price = getPrice(row);
        currentIncome += price;
        System.out.println("Ticket price: $" + price);
    }
    public void run() {
        int selection;
        do {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            selection = sc.nextInt();
            switch (selection) {
                case 1:
                    print();
                    break;
                case 2:
                    arrangeChair();
                    break;
                case 3:
                    statistics();
                    break;
                default:
                    break;
            }
        } while (selection != 0);
    }

    private void statistics() {
        System.out.printf("Number of purchased tickets: %d%n" +
                        "Percentage: %.2f%%%n" +
                        "Current income: $%d%n" +
                        "Total income: $%d%n",
                sold, 100. * sold / total, currentIncome, totalIncome);
    }
}
public class CinemaRoomManager5 {

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        CinemaRoom5 cinemaRoom = new CinemaRoom5(sc);
        cinemaRoom.run();
    }
}