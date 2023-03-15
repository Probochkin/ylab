package second.task.sequences;

import java.util.stream.IntStream;

public class SequencesImpl implements SequenceGenerator {
    @Override
    public void a(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i * 2 + " ");
        }
    }

    @Override
    public void b(int n) {
        for (int i = 1, previos = 0; i <= n; i++) {
            System.out.print(i + previos + " ");
            previos = i;
        }
    }

    @Override
    public void c(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i * i + " ");
        }
    }

    @Override
    public void d(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i * i * i + " ");
        }
    }

    @Override
    public void e(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(1 + " ");
            }
        }
    }

    @Override
    public void f(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print(-i + " ");
            } else {
                System.out.print(i + " ");
            }
        }
    }

    @Override
    public void g(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print(i * -i + " ");
            } else {
                System.out.print(i * i + " ");
            }
        }
    }

    @Override
    public void h(int n) {
        for (int i = 1, j = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print(0 + " ");
            } else {
                System.out.print(j++ + " ");
            }
        }
    }

    @Override
    public void i(int n) {
        for (int i = 1; i <= n; i++) {
            int fact = 1;
            for (int j = 1; j <= i; j++) {
                fact = fact * j;
        }
            System.out.print(fact + " ");

    }
    }


    @Override
    public void j(int n) {

            int previos = 1;
        int preprevios = 0;
        int number;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                System.out.print(1 + " ");
            } else {
                number = previos + preprevios;
                preprevios = previos;
                previos = number;
                System.out.print(number + " ");
            }
    }
    }
}
