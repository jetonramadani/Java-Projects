import java.util.Scanner;

public class MyFirstProjectJava3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int bubblegum = 202;
        final int toffee = 118;
        final int iceCream = 2250;
        final int milkChocolate = 1680;
        final int doughnut = 1075;
        final int pancake = 80;

        System.out.printf("""
                        Earned amount:
                        Bubblegum: $%d
                        Toffee: $%dd
                        Ice cream: $%d
                        Milk chocolate: $%d
                        Doughnut: $%d
                        Pancake: $%d%n
                        """, bubblegum,
                toffee,
                iceCream,
                milkChocolate,
                doughnut,
                pancake);

        double income = 0. + bubblegum + toffee +
                iceCream + milkChocolate + doughnut + pancake;

        System.out.printf("Income: $%.1f", income);

        System.out.println("Staff expenses:");
        int staffExpenses = sc.nextInt();

        System.out.println("Other expenses:");
        int otherExpenses = sc.nextInt();

        System.out.printf("Net income: $%.1f", income - staffExpenses - otherExpenses);
    }
}