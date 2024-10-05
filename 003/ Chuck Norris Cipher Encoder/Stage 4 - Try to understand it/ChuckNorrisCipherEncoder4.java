import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChuckNorrisCipherEncoder4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println("Input encoded string:");
            String word = sc.nextLine();

            String[] parts = word.split("\\s+");
            StringBuilder binaryConversion = new StringBuilder();
            for (int i = 0; i < parts.length; i += 2) {
                String digit = parts[i];
                int appears = parts[i + 1].length();
                if ("00".equals(digit)) {
                    binaryConversion.append("0".repeat(appears));
                } else {
                    binaryConversion.append("1".repeat(appears));
                }
            }

            StringBuilder decodedResult = new StringBuilder();

            for (int i = 0; i < binaryConversion.length(); i += 7) {
                String binary = binaryConversion.substring(i, i + 7);
                int decimalEquivalent = Integer.parseInt(binary, 2);
                decodedResult.append((char) decimalEquivalent);
            }
            System.out.println("The result:");
            System.out.println(decodedResult);
        }

    }

    public static void encode(String word) {
        System.out.println("The result:");
        StringBuilder res = new StringBuilder();
        for (Character ch : word.toCharArray()) {
            String binary = String.format("%07d", Integer.valueOf(Integer.toBinaryString(ch)));
            res.append(binary);
        }

        System.out.println(convertBinaryToZeros(res.toString()));
    }

    public static String convertBinaryToZeros(String binary) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        Character prev = binary.charAt(0);

        for (Character ch : binary.toCharArray()) {
            if (ch.equals(prev)) {
                ++count;
            } else {
                if (!res.isEmpty()) {
                    res.append(" ");
                }
                if (prev.equals('0')) {
                    res.append("00 ").append("0".repeat(count));
                } else {
                    res.append("0 ").append("0".repeat(count));
                }
                count = 1;
                prev = ch;
            }
        }
        if (!res.isEmpty()) {
            res.append(" ");
        }
        if (prev.equals('0')) {
            res.append("00 ").append("0".repeat(count));
        } else {
            res.append("0 ").append("0".repeat(count));
        }
        return res.toString();
    }
}