import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChuckNorrisCipherEncoder2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println("Input string:");
            String word = sc.nextLine();

            System.out.println("The result:");
            for (char ch : word.toCharArray()) {
                System.out.printf("%c = %07d%n", ch, Integer.valueOf(Integer.toBinaryString(ch)));
            }
        }

    }
}