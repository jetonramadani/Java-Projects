import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class RockPaperScissorsHelper5 {
    private static Map<String, List<String>> lossesFrom = Map.of(
            "rock", List.of("paper", "water", "air"),      // Rock loses to Paper, Water, Air
            "paper", List.of("scissors", "fire", "sponge"), // Paper loses to Scissors, Fire, Sponge
            "scissors", List.of("rock", "water", "air"),   // Scissors lose to Rock, Water, Air
            "fire", List.of("water", "rock", "air"),       // Fire loses to Water, Rock, Air
            "water", List.of("air", "sponge", "paper"),    // Water loses to Air, Sponge, Paper
            "air", List.of("rock", "fire", "scissors"),    // Air loses to Rock, Fire, Scissors
            "sponge", List.of("scissors", "fire", "rock")  // Sponge loses to Scissors, Fire, Rock
    );

    private final Map<String, Long> playerPoints;
    private final String filePath = "rating.txt";
    private String playerName;

    public RockPaperScissorsHelper5() {
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

    private void generateLossesFromMap(List<String> options) {
        lossesFrom = new HashMap<>();
        int N = options.size();

        for (int i = 0; i < N; i++) {
            String currentOption = options.get(i);

            // Determine the options that beat the current option
            List<String> losingOptions = new ArrayList<>();
            for (int j = 1; j <= N / 2; j++) {
                int losingIndex = (i + j) % N; // Wrap around using modulo
                losingOptions.add(options.get(losingIndex));
            }

            // Add to the map
            lossesFrom.put(currentOption, losingOptions);
        }

    }
    public void run(Scanner sc) {
        System.out.println("Enter your name: ");
        playerName = sc.nextLine();
        System.out.printf("Hello, %s%n", playerName);
        String options = sc.nextLine();
        if(options.isEmpty()) {
            options = "rock,paper,scissors";
        }
        this.generateLossesFromMap(List.of(options.split(",")));
        System.out.println("Okay, let's start");
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


public class RockPaperScissors5 {
    public static void main(String[] args) {
        RockPaperScissorsHelper5 rpcUtils = new RockPaperScissorsHelper5();
        try (Scanner sc = new Scanner(System.in)) {
            rpcUtils.run(sc);
        }
    }
}