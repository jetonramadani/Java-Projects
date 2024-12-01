public class CinemaRoomManager1 {
    private static void print(int[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print("S ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // Write your code here
        int[][] mat = new int[7][8];
        print(mat);
    }
}