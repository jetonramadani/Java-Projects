import java.util.*;

class RockPaperScissorsHelper2 {
    private static final Map<String, List<String>> lossesFrom = Map.of(
            "paper", List.of("scissors"),  // Paper loses to Scissors
            "scissors", List.of("rock"),  // Scissors loses to Rock
            "rock", List.of("paper")      // Rock loses to Paper
    );

    private List<String> getLosingOptions(String option) {
        return lossesFrom.getOrDefault(option, List.of(""));
    }

    public String getLosingOption(String playerOption) {
        return this.getLosingOptions(playerOption).get(0);
    }

    public String getRandomMove() {
        return lossesFrom.keySet()
                .stream().toList()
                .get(new Random().nextInt(0, lossesFrom.keySet().size()));
    }

    public String checkStatus(String playerPick, String pcPick) {
        if (Objects.equals(playerPick, pcPick)) {
            return String.format("There is a draw (%s)%n", playerPick);
        } else if (this.getLosingOptions(playerPick).contains(pcPick)) {
            return String.format("Sorry, but the computer chose %s%n", pcPick);
        } else {
            return String.format("Well done. The computer chose %s and failed%n", pcPick);
        }
    }
}

public class RockPaperScissors2 {
    public static void main(String[] args) {
        // write your code here
        RockPaperScissorsHelper2 rpcUtils = new RockPaperScissorsHelper2();
        try (Scanner sc = new Scanner(System.in)) {
            String playerOption = sc.next();
            String pcOption = rpcUtils.getRandomMove();
            System.out.println(rpcUtils.checkStatus(playerOption, pcOption));
        }
    }
}
