package first.task;

import java.util.Scanner;

public class Pell {
    public static void main(String[] args) {
        System.out.println("Введите число от 0 до 30");
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            if (n >= 0 && n <= 30) {
                if (n == 0) {
                    System.out.println(0);
                }
                if (n == 1) {
                    System.out.println(1);
                }
                if (n > 1) {
                    long numberPells = 0;
                    long previous = 1;
                    long prePrevious = 0;
                    for (int i = 2; i <= n; i++) {
                        numberPells = 2 * previous + prePrevious;
                        prePrevious = previous;
                        previous = numberPells;
                    }
                    System.out.println(numberPells);
                }

            } else {
                System.out.println("Число " + n + " не входит в диапазон от 0 до 30");
            }
        }

    }

}
