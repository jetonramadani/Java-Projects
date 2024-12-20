import java.util.*;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

abstract class NumCheck8 {
    abstract boolean checkNumber(long number);
}

class NumException8 extends Throwable {
    String str;

    NumException8(String str) {
        this.str = str;
    }

    @Override
    public String getMessage() {
        return str;
    }
}

class AmazingNumber8Helper {
    private final Scanner sc;
    private long num;
    private final HashMap<String, NumCheck8> checkers;
    private final HashMap<String, String> mutuallyExclusive;

    public AmazingNumber8Helper(Scanner sc) {
        this.sc = sc;
        checkers = new HashMap<>() {
            {
                put("EVEN", new NumCheck8() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 0;
                    }
                });
                put("ODD", new NumCheck8() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 1;
                    }
                });
                put("BUZZ", new NumCheck8() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 7 == 0 || number % 10 == 7;
                    }
                });
                put("DUCK", new NumCheck8() {
                    @Override
                    public boolean checkNumber(long number) {
                        return Long.toString(number).contains("0");
                    }
                });
                put("PALINDROMIC", new NumCheck8() {
                    @Override
                    public boolean checkNumber(long number) {
                        StringBuilder sb = new StringBuilder(Long.toString(number));
                        return sb.toString().equals(sb.reverse().toString());
                    }
                });
                put("GAPFUL", new NumCheck8() {
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
                put("SPY", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        long sum = 0L;
                        long product = 1L;
                        for (char ch : String.valueOf(number).toCharArray()) {
                            int num = ch - '0';
                            sum += num;
                            product *= num;
                        }
                        return sum == product;
                    }
                });
                put("SQUARE", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        double res = Math.sqrt(number);
                        return res == Math.floor(res);
                    }
                });
                put("SUNNY", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        double res = Math.sqrt(number + 1);
                        return res == Math.floor(res);
                    }
                });
                put("JUMPING", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        if (number < 10) {
                            return true;
                        }
                        String str = String.valueOf(number);
                        for (int i = 1; i < str.length(); i++) {
                            int j = i - 1;
                            if (str.charAt(i) + 1 != str.charAt(j)
                                    && str.charAt(i) != str.charAt(j) + 1) {
                                return false;
                            }

                        }
                        return true;
                    }
                });
                put("HAPPY", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        return checkHappy(number);
                    }
                });
                put("SAD", new NumCheck8() {
                    @Override
                    boolean checkNumber(long number) {
                        return !checkHappy(number);
                    }
                });
            }
        };
        mutuallyExclusive = new HashMap<>() {
            {
                put("EVEN", "ODD");
                put("DUCK", "SPY");
                put("SUNNY", "SQUARE");
                put("SAD", "HAPPY");
                put("ODD", "EVEN");
                put("SPY", "DUCK");
                put("SQUARE", "SUNNY");
                put("HAPPY", "SAD");
            }
        };
    }

    private boolean checkHappy(long number) {
        do {
            long sum = 0;
            while (number != 0) {
                long last = number % 10;
                sum += last * last;
                number /= 10;
            }
            number = sum;
        } while (number > 9);
        return number == 1;
    }

    private void printRules() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    private byte processRequest() throws NumException8 {
        System.out.println("Enter a request:");
        String str = sc.nextLine().toUpperCase();
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
            throw new NumException8("The first parameter should be a natural number or zero.");
        }
        if (num == 0) {
            return 0;
        }
        if (parts.length == 1) {
            printInfoSingle();
            return 1;
        } else {
            try {
                long n2 = Long.parseLong(parts[1]);
                if (n2 < 0) {
                    throw new Exception();
                }
                if (parts.length == 2) {
                    printInfoDouble(n2);
                } else {
                    printContainsOrNot(n2, Arrays.stream(parts)
                            .skip(2).distinct()
                            .toArray(String[]::new));
                }
            } catch (Exception e) {
                throw new NumException8("The second parameter should be a natural number.");
            }
            return 2;
        }

    }

    private void formatProperty(String name, NumCheck8 check) {
        System.out.printf("%12s: %s%n", name, check.checkNumber(num));
    }

    private void printDoubleOrMoreFormatted(String accepted) {
        System.out.println(num + " is " + accepted.substring(1, accepted.length() - 1));
    }

    private String getOkayDoubleOrMore() {
        List<String> strings = new ArrayList<>();
        for (String check : checkers.keySet()) {
            if (checkers.get(check).checkNumber(num)) {
                strings.add(check);
            }
        }
        return strings.toString();
    }

    private void printInfoDouble(long up) {
        for (long i = 0; i < up; i++) {
            printDoubleOrMoreFormatted(getOkayDoubleOrMore());
            ++num;
        }
    }

    private void MutualExclusiveThrower(String first, String second) throws NumException8 {
        throw new NumException8(String.format("The request contains mutually exclusive properties: [%s, %s]\n" +
                "There are no numbers with these properties", first, second));
    }

    private boolean contains(String[] constraints, String item) {
        for (String s : constraints) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private void mutualChecker(String[] constraints) throws NumException8 {
        for (String s : constraints) {
            if (s.startsWith("-")) {
                String withoutNegation = s.substring(1);
                if (contains(constraints, withoutNegation)) {
                    MutualExclusiveThrower(s, withoutNegation);
                }
                if (mutuallyExclusive.containsKey(withoutNegation)) {
                    String mutualExcl = "-" + mutuallyExclusive.get(withoutNegation);
                    if (contains(constraints, mutualExcl)) {
                        MutualExclusiveThrower(s, mutualExcl);
                    }
                }
            } else {
                if (contains(constraints, "-" + s)) {
                    MutualExclusiveThrower(s, "-" + s);
                }
                if (mutuallyExclusive.containsKey(s)) {
                    String mutualExcl = mutuallyExclusive.get(s);
                    if (contains(constraints, mutualExcl)) {
                        MutualExclusiveThrower(s, mutualExcl);
                    }
                }
            }
        }
    }

    private void printContainsOrNot(long total, String... constraints) throws NumException8 {
        List<String> wrong = new ArrayList<>();

        for (String s : constraints) {
            if (s.startsWith("-")) {
                if (!checkers.containsKey(s.substring(1))) {
                    wrong.add(s);
                }
            } else {
                if (!checkers.containsKey(s)) {
                    wrong.add(s);
                }
            }
        }
        if (wrong.size() != 0) {
            if (wrong.size() == 1) {
                throw new NumException8("The property [" + wrong + "] is wrong.\n" +
                        "Available properties: " + checkers.keySet());
            } else {
                throw new NumException8("The properties [" + wrong + "] are wrong.\n" +
                        "Available properties: " + checkers.keySet());
            }
        }
        mutualChecker(constraints);
        int count = 1;
        while (count <= total) {
            boolean flag = true;
            for (String s : constraints) {
                if (s.startsWith("-")) {
                    flag = !checkers.get(s.substring(1)).checkNumber(num);
                } else {
                    flag = checkers.get(s).checkNumber(num);
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                printDoubleOrMoreFormatted(getOkayDoubleOrMore());
                ++count;
            }
            ++num;
        }

    }

    private void printInfoSingle() {
        System.out.printf("Properties of %d%n", num);
        checkers.forEach(this::formatProperty);
    }

    public void run() {
        System.out.println("Welcome to Amazing Numbers!");
        printRules();
        byte req = -1;
        do {
            try {
                req = processRequest();
            } catch (NumException8 e) {
                System.out.println(e.getMessage());
            }
        } while (req != 0);
        System.out.println("Goodbye!");
    }
}

public class AmazingNumbers8 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        AmazingNumber8Helper number = new AmazingNumber8Helper(sc);
        number.run();
    }
}