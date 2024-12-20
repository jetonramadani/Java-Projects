import java.util.Scanner;

class NotNaturalNumber2 extends Throwable {
    @Override
    public String getMessage() {
        return "This number is not natural!";
    }
}

class AmazingNumber2 {
    private final int num;
    private final Scanner sc;

    public AmazingNumber2(Scanner sc) throws NotNaturalNumber2 {
        this.sc = sc;
        System.out.println("Enter a natural number:");
        num = sc.nextInt();
        if (num <= 0)
            throw new NotNaturalNumber2();
        printInfo();
    }

    private boolean evenChecker() {
        return num % 2 == 0;
    }

    private boolean buzzChecker() {
        return num % 7 == 0 || num % 10 == 7;
    }

    private boolean duckChecker() {
        return Integer.toString(num).contains("0");
    }

    private String formatProperty(String name, boolean value) {
        return String.format("%12s: %s", name, value);
    }

    private void printInfo() {
        System.out.printf("Properties of %d%n", num);
        System.out.println(formatProperty("even", evenChecker()));
        System.out.println(formatProperty("odd", !evenChecker()));
        System.out.println(formatProperty("buzz", buzzChecker()));
        System.out.println(formatProperty("duck", duckChecker()));
    }
}

public class AmazingNumbers2 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        try {
            AmazingNumber2 number = new AmazingNumber2(sc);
        } catch (NotNaturalNumber2 e) {
            System.out.println(e.getMessage());
        }
    }
}