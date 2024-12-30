import java.util.List;
import java.util.Map;
import java.util.Scanner;

class RockPaperScissorsHelper1 {
    private static final Map<String, List<String>> lossesFrom = Map.of(
            "paper", List.of("scissors"),  // Paper loses to Scissors
            "scissors", List.of("rock"),  // Scissors loses to Rock
            "rock", List.of("paper")      // Rock loses to Paper
    );

    public String getLosingOption(String playerOption) {
        return lossesFrom.getOrDefault(playerOption, List.of("")).get(0);
    }
}

public class RockPaperScissors1 {
    public static void main(String[] args) {
        // write your code here
        RockPaperScissorsHelper1 rpcUtils = new RockPaperScissorsHelper1();
        try (Scanner sc = new Scanner(System.in)) {
            String playerOption = sc.next();
            System.out.printf("Sorry, but the computer chose %s%n", rpcUtils.getLosingOption(playerOption));
        }
    }
}
