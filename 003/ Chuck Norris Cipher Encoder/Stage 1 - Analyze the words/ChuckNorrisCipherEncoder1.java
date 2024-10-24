import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChuckNorrisCipherEncoder1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println("Input string:");
            String word = sc.nextLine();

            for (char ch : word.toCharArray()) {
                System.out.printf("%c ", ch);
            }
        }

    }
}