import java.util.*;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

abstract class NumCheck7 {
    abstract boolean checkNumber(long number);
}

class NumException7 extends Throwable {
    String str;

    NumException7(String str) {
        this.str = str;
    }

    @Override
    public String getMessage() {
        return str;
    }
}

class AmazingNumber7Helper {
    private final Scanner sc;
    private long num;
    private final HashMap<String, NumCheck7> checkers;

    public AmazingNumber7Helper(Scanner sc) {
        this.sc = sc;
        checkers = new HashMap<>() {
            {
                put("EVEN", new NumCheck7() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 0;
                    }
                });
                put("ODD", new NumCheck7() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 2 == 1;
                    }
                });
                put("BUZZ", new NumCheck7() {
                    @Override
                    public boolean checkNumber(long number) {
                        return number % 7 == 0 || number % 10 == 7;
                    }
                });
                put("DUCK", new NumCheck7() {
                    @Override
                    public boolean checkNumber(long number) {
                        return Long.toString(number).contains("0");
                    }
                });
                put("PALINDROMIC", new NumCheck7() {
                    @Override
                    public boolean checkNumber(long number) {
                        StringBuilder sb = new StringBuilder(Long.toString(number));
                        return sb.toString().equals(sb.reverse().toString());
                    }
                });
                put("GAPFUL", new NumCheck7() {
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
                put("SPY", new NumCheck7() {
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
                put("SQUARE", new NumCheck7() {
                    @Override
                    boolean checkNumber(long number) {
                        double res = Math.sqrt(number);
                        return res == Math.floor(res);
                    }
                });
                put("SUNNY", new NumCheck7() {
                    @Override
                    boolean checkNumber(long number) {
                        double res = Math.sqrt(number + 1);
                        return res == Math.floor(res);
                    }
                });
                put("JUMPING", new NumCheck7() {
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
            }
        };
    }

    private void printRules() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    private byte processRequest() throws NumException7 {
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
            throw new NumException7("The first parameter should be a natural number or zero.");
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
                throw new NumException7("The second parameter should be a natural number.");
            }
            return 2;
        }

    }

    private void formatProperty(String name, NumCheck7 check) {
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

    private void throwMutuallyExclusive(byte state, int type) throws NumException7 {
        StringBuilder sb = new StringBuilder("The request contains mutually exclusive properties: ");
        if (state == 2) {
            switch (type) {
                case 1:
                    sb.append("[ODD, EVEN]\n");
                    break;
                case 2:
                    sb.append("[DUCK, SPY]\n");
                    break;
                case 3:
                    sb.append("[SUNNY SQUARE]");
                    break;
            }
            sb.append("There are no numbers with these properties.");
            throw new NumException7(sb.toString());
        }
    }

    private void printContainsOrNot(long total, String... constraints) throws NumException7 {
        List<String> wrong = new ArrayList<>();
        byte[] states = new byte[3];
        for (String s : constraints) {
            if (!checkers.containsKey(s)) {
                wrong.add(s);
            }
            if (s.matches("EVEN") || s.matches("ODD")) {
                ++states[0];
            } else if (s.matches("DUCK") || s.matches("SPY")) {
                ++states[1];
            } else if (s.matches("SUNNY") || s.matches("SQUARE")) {
                ++states[2];
            }
        }
        if (wrong.size() != 0) {
            if (wrong.size() == 1) {
                throw new NumException7("The property [" + wrong + "] is wrong.\n" +
                        "Available properties: " + checkers.keySet());
            } else {
                throw new NumException7("The properties [" + wrong + "] are wrong.\n" +
                        "Available properties: " + checkers.keySet());
            }
        }
        for (int i = 0; i < states.length; i++) {
            throwMutuallyExclusive(states[i], i);
        }
        int count = 1;
        while (count <= total) {
            boolean flag = true;
            for (String s : constraints) {
                flag = checkers.get(s).checkNumber(num);
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
            } catch (NumException7 e) {
                System.out.println(e.getMessage());
            }
        } while (req != 0);
        System.out.println("Goodbye!");
    }
}

public class AmazingNumbers7 {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        AmazingNumber7Helper number = new AmazingNumber7Helper(sc);
        number.run();
    }
}