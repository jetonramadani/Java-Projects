import java.util.*;

class RockPaperScissorsHelper3 {
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

    public boolean checkValidOption(String option) {
        return lossesFrom.containsKey(option);
    }

    public void run(Scanner sc) {
        while (true) {
            String playerOption = sc.next();
            if (playerOption.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }
            if (this.checkValidOption(playerOption)) {
                String pcOption = this.getRandomMove();
                System.out.print(this.checkStatus(playerOption, pcOption));
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}

public class RockPaperScissors3 {
    public static void main(String[] args) {
        // write your code here
        RockPaperScissorsHelper3 rpcUtils = new RockPaperScissorsHelper3();
        try (Scanner sc = new Scanner(System.in)) {
            rpcUtils.run(sc);
        }
    }
}
