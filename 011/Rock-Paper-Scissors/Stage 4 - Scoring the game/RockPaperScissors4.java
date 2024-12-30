import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class RockPaperScissorsHelper4 {
    private static final Map<String, List<String>> lossesFrom = Map.of(
            "paper", List.of("scissors"),  // Paper loses to Scissors
            "scissors", List.of("rock"),  // Scissors loses to Rock
            "rock", List.of("paper")      // Rock loses to Paper
    );
    private final Map<String, Long> playerPoints;
    private final String filePath = "rating.txt";
    private String playerName;

    public RockPaperScissorsHelper4() {
        playerPoints = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            reader.lines()
                    .forEach(line -> {
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 2) {
                            playerPoints.put(parts[0], Long.parseLong(parts[1]));
                        }
                    });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

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
            this.playerPoints.computeIfPresent(playerName, (s, aLong) -> aLong + 50);
            return String.format("There is a draw (%s)%n", playerPick);
        } else if (this.getLosingOptions(playerPick).contains(pcPick)) {
            return String.format("Sorry, but the computer chose %s%n", pcPick);
        } else {
            this.playerPoints.computeIfPresent(playerName, (s, aLong) -> aLong + 100);
            return String.format("Well done. The computer chose %s and failed%n", pcPick);
        }
    }

    public boolean checkValidOption(String option) {
        return lossesFrom.containsKey(option);
    }

    public void run(Scanner sc) {
        System.out.println("Enter your name: ");
        playerName = sc.nextLine();
        System.out.printf("Hello, %s%n", playerName);

        playerPoints.putIfAbsent(playerName, 0L);

        while (true) {
            String playerOption = sc.next();
            if (playerOption.equals("!exit")) {
                System.out.println("Bye!");
                break;

            }
            if (playerOption.equals("!rating")) {
                System.out.println("Your rating: " + this.playerPoints.get(playerName));
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


public class RockPaperScissors4 {
    public static void main(String[] args) {
        RockPaperScissorsHelper4 rpcUtils = new RockPaperScissorsHelper4();
        try (Scanner sc = new Scanner(System.in)) {
            rpcUtils.run(sc);
        }
    }
}
