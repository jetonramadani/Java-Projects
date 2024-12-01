import java.util.Scanner;

class NotNaturalNumber1 extends Throwable {
    @Override
    public String getMessage() {
        return "This number is not natural!";
    }
}

class AmazingNumber1 {
    private final int num;
    private final Scanner sc;

    public AmazingNumber1(Scanner sc) throws NotNaturalNumber1 {
        this.sc = sc;
        System.out.println("Enter a natural number:");
        num = sc.nextInt();
        if (num <= 0)
            throw new NotNaturalNumber1();
        printInfo();
    }

    private String parity() {
        return "This number is " +
                (num % 2 == 0 ? "Even." : "Odd.");
    }

    private String buzzChecker() {
        boolean isBuzz;
        String explanation = String.format("Explanation:%n");
        if (num % 7 == 0 || num % 10 == 7) {
            isBuzz = true;
            if (num % 7 == 0 && num % 10 == 7) {
                explanation += String.format("%d is divisible by 7 and ends with 7.", num);
            } else if (num % 7 == 0) {
                explanation += String.format("%d is divisible by 7.", num);
            } else {
                explanation += String.format("%d ends with 7.", num);
            }
        } else {
            isBuzz = false;
            explanation += String.format("%d is neither divisible by 7 nor does it end with 7.", num);
        }
        return String.format("It is%s a Buzz number.%n%s",
                isBuzz ? "" : " not", explanation);
    }

    private void printInfo() {
        System.out.println(parity());
        System.out.println(buzzChecker());
    }
}

public class AmazingNumbers1 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        try {
            AmazingNumber1 number = new AmazingNumber1(sc);
        } catch (NotNaturalNumber1 e) {
            System.out.println(e.getMessage());
        }
    }
}