import java.util.Scanner;

public class TicTacToeSimple3 {
    public static int checkOk(char x, char y, char z) {
        if (x != '_' && x == y && x == z) {
            return x == 'X' ? 1 : -1;
        }
        return 0;
    }
    public static char checkWin(char[] plays) {
        int x = 0;
        int o = 0;
        int[][] winCombos = {
                {0, 1, 2},
                {0, 3, 6},
                {0, 4, 8},
                {1, 4, 7},
                {3, 4, 5},
                {2, 5, 8},
                {2, 4, 6},
                {6, 7, 8}
        };
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
            int x_ = 0;
            int o_ = 0;
            for (char ch : plays) {
                if (ch == 'X') {
                    ++x_;
                } else if (ch == 'O') {
                    ++o_;
                }
            }
            String gameState = new String(plays);
            if (gameState.contains("_")) {
                return x_ == o_ || x_ == o_ + 1 ? 'N' : 'I';
            }
            return x_ == o_ || x_ == o_ + 1 ? 'D' : 'I';
        }
    }
    public static String getGameCheck(char[] plays) {
        switch (checkWin(plays)) {
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
    public static void printBoard(char[] plays) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(plays[i * 3 + j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        System.out.print("Enter cells: ");
        char[] plays = sc.next().toCharArray();
        printBoard(plays);
        System.out.println(getGameCheck(plays));
    }
}