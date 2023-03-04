package first.task;

import java.util.Random;
import java.util.Scanner;

public class Guess {

    public static void main(String[] args) {
        int number = new Random().nextInt(100);
        int maxAttempts = 10;
        int i = 0;
        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");
        while (i != maxAttempts) {
            Scanner scanner = new Scanner(System.in);
            int userNumber = scanner.nextInt();
            i++;
            if (userNumber > number) {
                System.out.println(String.format("Мое число меньше! Осталось %d попыток", maxAttempts - i));
            }
            if (userNumber < number) {
                System.out.println(String.format("Мое число больше! Осталось %d попыток", maxAttempts - i));
            }
            if (userNumber == number) {
                System.out.println(String.format("Ты угадал с %d попытки", i));
                break;
            }
            if (i == 10) {
                System.out.println("Ты не угадал");
            }
        }
    }
}
