package second.task.complexNumbers;

public class ComplexNumbersTest {
    public static void main(String[] args){
        ComplexNumbers n1 = new ComplexNumbers(3.0, 3.0);
        ComplexNumbers n2 = new ComplexNumbers(5.0, 6.0);
        ComplexNumbers n3 = new ComplexNumbers(5.0);

        System.out.println(String.format("Сумма двух комплекстных чисел %s и %s равна %s", n1.toString(),
                n2.toString(),n1.sum(n2)));

        System.out.println(String.format("Разность двух комплекстных чисел %s и %s равна %s", n1.toString(),
                n2.toString(),n1.subtraction(n2)));

        System.out.println(String.format("Произведение двух комплекстных чисел %s и %s равна %s", n1.toString(),
                n2.toString(),n1.multiplication(n2)));

        System.out.println(String.format("Модуль комплекстного числа %s равен %s",n3.toString(),n3.abs()));

    }
}
