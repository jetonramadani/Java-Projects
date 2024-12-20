import java.util.*;

abstract class NumCheck4 {
    final String name;

    public NumCheck4(String name) {
        this.name = name;
    }
    abstract boolean checkNumber(long number);
}
class NumException4 extends Throwable {
    String str;
    NumException4(String str) {
        this.str = str;
    }
    @Override
    public String getMessage() {
        return str;
    }
}

class AmazingNumber4Helper {
    private final Scanner sc;
    private long num;
    private final List<NumCheck4> checkers;
    public AmazingNumber4Helper(Scanner sc) {
        this.sc = sc;
        checkers = new ArrayList<>() {
            {
                add(new NumCheck4("even") {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 0;
                    }
                });
                add(new NumCheck4("odd") {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 1;
                    }
                });
                add(new NumCheck4("buzz") {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 7 == 0 || number % 10 == 7;
                    }
                });
                add(new NumCheck4("duck") {
                    @Override
                    public boolean checkNumber(long number) {
                        return Long.toString(number).contains("0");
                    }
                });
                add(new NumCheck4("palindromic") {
                    @Override
                    public boolean checkNumber(long number) {
                        StringBuilder sb = new StringBuilder(Long.toString(number));
                        return sb.toString().equals(sb.reverse().toString());
                    }
                });
                add(new NumCheck4("gapful") {
                    @Override
                    boolean checkNumber(long number) {
                        String str = Long.toString(number);
                        if (str.length() < 3) {
                            return false;
                        } else {
                            long num = Long.parseLong(str.charAt(0) + ""
                                    + str.charAt(str.length() - 1));
                            return number % num == 0;
                        }
                    }
                });
            }
        };
    }
    private void printRules() {
        System.out.println("\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }
    private byte processRequest() throws NumException4 {
        System.out.println("Enter a request:");
        String str = sc.nextLine();
        if (str.trim().length() == 0) {
            printRules();
            return -1;
        }
        String[] parts = str.split("\\s+");
        try {
            num = Long.parseLong(parts[0]);
            if (num < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new NumException4("The first parameter should be a natural number or zero.");
        }
        if (num == 0) {
            return 0;
        }
        if (parts.length == 1) {
            printInfoSingle();
            return 1;
        } else if (parts.length == 2) {
            try {
                long n2 = Long.parseLong(parts[1]);
                if (n2 < 0) {
                    throw new Exception();
                }
                printInfoDouble(n2);
            } catch (Exception e) {
                throw new NumException4("The second parameter should be a natural number.");
            }
            return 2;
        }

        return -1;
    }
    private void formatProperty(NumCheck4 check) {
        System.out.printf("%12s: %s%n", check.name, check.checkNumber(num));
    }
    private void printInfoDouble(long up) {
        for (long i = 0; i < up; i++) {
            List<String> strings = new ArrayList<>();
            for (NumCheck4 check : checkers) {
                if (check.checkNumber(num)) {
                    strings.add(check.name);
                }
            }
            String accepted = strings.toString();
            System.out.println(num + " is " + accepted.substring(1, accepted.length() - 1));
            ++num;
        }
    }
    private void printInfoSingle() {
        System.out.printf("Properties of %d%n", num);
        checkers.forEach(this::formatProperty);
    }
    public void run(){
        System.out.println("Welcome to Amazing Numbers!");
        printRules();
        byte req = -1;
        do {
            try {
                req = processRequest();
            } catch (NumException4 e) {
                System.out.println(e.getMessage());
            }
        } while (req != 0);
        System.out.println("Goodbye!");
    }
}
public class AmazingNumbers4 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        AmazingNumber4Helper number = new AmazingNumber4Helper(sc);
        number.run();
    }
}