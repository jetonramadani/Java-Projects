import java.util.HashMap;
import java.util.Scanner;
class TickTacSimple5 {
    private char[] plays;
    private final Scanner sc;
    private int x_;
    private int o_;
    private boolean xMove;
    static final int [][] winCombos = {
            {0, 1, 2},
            {0, 3, 6},
            {0, 4, 8},
            {1, 4, 7},
            {3, 4, 5},
            {2, 5, 8},
            {2, 4, 6},
            {6, 7, 8}
    };
    static final HashMap<String, Integer> movesToIndex =
            new HashMap<>() {
                {
                    put("1.1", 0);
                    put("1.2", 1);
                    put("1.3", 2);
                    put("2.1", 3);
                    put("2.2", 4);
                    put("2.3", 5);
                    put("3.1", 6);
                    put("3.2", 7);
                    put("3.3", 8);
                }
            };
    TickTacSimple5(Scanner sc) {
        this.sc = sc;
        setCells();
        printBoard();
    }
    private void setCells() {
//        System.out.print("Enter cells: ");
//        plays = sc.next().toCharArray();
        plays = "_________".toCharArray();
        for (char ch : plays) {

            if (ch == 'X') {
                ++x_;
            } else if (ch == 'O') {
                ++o_;
            }
        }
//        xMove = x_ <= o_;
        xMove = true;
    }
    public void move() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String search = sc.nextInt() + "." + sc.nextInt();
            try {
                Double.parseDouble(search);
                if (movesToIndex.containsKey(search)) {
                    int ind = movesToIndex.get(search);
                    if (plays[ind] == '_') {
                        plays[ind] = xMove ? 'X' : 'O';
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
        printBoard();
        xMove = !xMove;
    }
    private int checkOk(char x, char y, char z) {
        if (x != '_' && x == y && x == z) {
            return x == 'X' ? 1 : -1;
        }
        return 0;
    }
    private char checkWin() {
        int x = 0;
        int o = 0;
        for (int[] combo : winCombos) {
            int res = checkOk(plays[combo[0]],
                    plays[combo[1]],
                    plays[combo[2]]);
            if (res == 1) {
                ++x;
            } else if (res == -1) {
                ++o;
            }
        }
        if (o != 0 && x != 0) {
            return 'I';
        } else if (o != 0) {
            return 'O';
        } else if (x != 0) {
            return 'X';
        } else {
            String gameState = new String(plays);
            if (gameState.contains("_")) {
                return x_ == o_ || x_ == o_ + 1 ? 'N' : 'I';
            }
            return x_ == o_ || x_ == o_ + 1 ? 'D' : 'I';
        }

    }
    public String getGameCheck() {
        switch (checkWin()) {
            case 'X' :
                return "X wins";
            case 'O' :
                return "O wins";
            case 'I':
                return "Impossible";
            case 'D':
                return "Draw";
            case 'N':
                return "Game not finished";
            default:
                return "";
        }
    }
    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (plays[i * 3 + j] != '_') {
                    System.out.print(plays[i * 3 + j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
public class TicTacToeSimple5 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        TickTacSimple5 ticTacToe = new TickTacSimple5(sc);
        String result;
        do {
            ticTacToe.move();
            result = ticTacToe.getGameCheck();
        } while (!result.endsWith("wins") && !result.equals("Draw"));
        System.out.println(result);
    }
}