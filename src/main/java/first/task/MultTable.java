package first.task;

public class MultTable {
    public static void main(String[] args) {
        for(int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.println(String.format("%d x %d = ", i, j) + i * j);
            }
        }
    }

}
