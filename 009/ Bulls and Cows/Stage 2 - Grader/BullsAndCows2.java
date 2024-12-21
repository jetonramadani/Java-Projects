
import java.util.*;

class BullAndCows2Helper {
    private static final Random rand = new Random();
    private Character[] num;
    private LinkedHashSet<Character> digits;
    private int chars;
    private int length;

    private char getValue(int i) {
        if (i < 10) {
            return (char) ('0' + i);
        } else {
            return (char) ('a' + i - 10);
        }
    }

    private void generate() {
        digits.clear();
        while (digits.size() != length) {
            char ch = getValue(rand.nextInt(chars));
            digits.add(ch);
        }
        num = new Character[length];
        digits.toArray(num);
    }

    public String check(String input) {
        HashSet<Character> guessed = new HashSet<>();
        int bulls = 0;
        int cows = 0;
        int i = 0;
        for (char ch : input.toCharArray()) {
            if (!guessed.contains(ch)) {
                if (num[i] == ch) {
                    ++bulls;
                } else if (digits.contains(ch)) {
                    ++cows;
                }
                guessed.add(ch);
            }
            ++i;
        }
        if (cows == 0 && bulls == 0) {
            return "None.";
        } else if (bulls == 0) {
            return cows + " cow" + (cows > 1 ? "s." : ".");
        } else if (cows == 0) {
            return bulls + " bull" + (bulls > 1 ? "s." : ".");
        } else {
            return bulls + " bull" + (bulls > 1 ? "s and " : " and ") +
                    cows + " cow" + (cows > 1 ? "s." : ".");
        }
    }

    public BullAndCows2Helper() {
        this.chars = 10;
        this.length = 4;
        digits = new LinkedHashSet<>();
        generate();
    }

    public String getNum() {
        return digits.toString().substring(1, length * 3 - 1)
                .replaceAll(", ", "");
    }
}

public class BullsAndCows2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BullAndCows2Helper numberCheck = new BullAndCows2Helper();

        String input = sc.next();
        System.out.print("Grade: " + numberCheck.check(input));
        System.out.printf(" The secret code is %s.", numberCheck.getNum());
    }
}