package first.task;

import java.util.Scanner;

public class Stars {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            for (int line = 0; line < n; line++) {
                for (int column = 0; column < m; column++) {
                    System.out.print(template + " ");
                }
                System.out.println();
            }
        }
    }
}
