public class MyFirstProjectJava2 {
    public static void main(String[] args) {

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
                        Pancake: $%d
                        """, bubblegum,
                toffee,
                iceCream,
                milkChocolate,
                doughnut,
                pancake);

        System.out.printf("Income: $%.1f", 0. + bubblegum + toffee +
                iceCream + milkChocolate + doughnut + pancake);
    }
}