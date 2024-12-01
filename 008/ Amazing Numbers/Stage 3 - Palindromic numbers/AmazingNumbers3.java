import java.util.*;

abstract class NumCheck3 {
    String name;

    public NumCheck3(String name) {
        this.name = name;
    }
    abstract void checkNumber(long number);
}
class NotNaturalNumber3 extends Throwable {
    @Override
    public String getMessage() {
        return "The first parameter should be a natural number or zero.";
    }
}

class AmazingNumber3 {
    private long num;
    private final Scanner sc;
    List<NumCheck3> checkers;
    public AmazingNumber3(Scanner sc) {
        this.sc = sc;
        checkers = new ArrayList<>() {
            {
                add(new NumCheck3("even") {
                    @Override
                    public void checkNumber(long number) {
                        formatProperty(name, number % 2 == 0);
                    }
                });
                add(new NumCheck3("odd") {
                    @Override
                    public void checkNumber(long number) {
                        formatProperty(name, number % 2 == 1);
                    }
                });
                add(new NumCheck3("buzz") {
                    @Override
                    public void checkNumber(long number) {
                        formatProperty(name, number % 7 == 0 || number % 10 == 7);
                    }
                });
                add(new NumCheck3("duck") {
                    @Override
                    public void checkNumber(long number) {
                        formatProperty(name, Long.toString(number).contains("0"));
                    }
                });
                add(new NumCheck3("palindromic") {
                    @Override
                    public void checkNumber(long number) {
                        StringBuilder sb = new StringBuilder(Long.toString(number));
                        formatProperty(name, sb.toString().equals(sb.reverse().toString()));
                    }
                });
            }
        };
        printInfo();
    }
    private void printWelcome() {
        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.");
    }
    private boolean processRequest() throws NotNaturalNumber3 {
        System.out.println("Enter a request:");
        this.num = sc.nextLong();
        if (num < 0) {
            throw new NotNaturalNumber3();
        }
        else if (num == 0) {
            return false;
        }
        printInfo();
        return true;
    }
    private void formatProperty(String name, boolean value) {
        System.out.printf("%12s: %s%n", name, value);
    }
    private void printInfo() {
        System.out.printf("Properties of %d%n", num);
        checkers.forEach(obj -> obj.checkNumber(num));
    }
    public void run(){
        printWelcome();
        boolean req = true;
        do {
            try {
                req = processRequest();
            } catch (NotNaturalNumber3 e) {
                System.out.println(e.getMessage());
            }
        } while (req);
        System.out.println("Goodbye!");
    }
}
public class AmazingNumbers3 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        AmazingNumber3 number = new AmazingNumber3(sc);
        number.run();
    }
}