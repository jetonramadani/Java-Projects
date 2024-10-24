import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChuckNorrisCipherEncoder5 {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8)) {
            String operation = "";
            while (!"exit".equals(operation)) {
                System.out.println("Please input operation (encode/decode/exit):");
                operation = sc.nextLine();
                switch (operation) {
                    case "encode" -> {
                        System.out.println("Input string: ");
                        String wordToEncode = sc.nextLine();
                        encode(wordToEncode);
                    }
                    case "decode" -> {
                        System.out.println("Input encoded string");
                        String wordToDecode = sc.nextLine();
                        try {
                            decode(wordToDecode);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case "exit" -> System.out.println("Bye!");
                    default -> System.out.printf("There is no '%s' operation%n", operation);
                }
            }
        }

    }

    public static void checkOnlyZeros(String word) {
        for (char ch : word.toCharArray()) {
            if (ch != '0') {
                throw new RuntimeException("Encoded string is not valid.");
            }
        }

    }

    public static void decode(String word) {
        String[] parts = word.split("\\s+");
        StringBuilder binaryConversion = new StringBuilder();
        for (int i = 0; i < parts.length; i += 2) {
            String digit = parts[i];
            checkOnlyZeros(parts[i + 1]);
            int appears = parts[i + 1].length();
            if ("00".equals(digit)) {
                binaryConversion.append("0".repeat(appears));
            } else if ("0".equals(digit)) {
                binaryConversion.append("1".repeat(appears));
            } else {
                throw new RuntimeException("Encoded string is not valid.");
            }
        }

        StringBuilder decodedResult = new StringBuilder();
        if (binaryConversion.length() % 7 != 0) {
            throw new RuntimeException("Encoded string is not valid.");
        }
        for (int i = 0; i < binaryConversion.length(); i += 7) {
            String binary = binaryConversion.substring(i, i + 7);
            int decimalEquivalent = Integer.parseInt(binary, 2);
            decodedResult.append((char) decimalEquivalent);
        }
        System.out.println("Decoded string:");
        System.out.println(decodedResult);
    }

    public static void encode(String word) {
        System.out.println("Encoded string:");
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