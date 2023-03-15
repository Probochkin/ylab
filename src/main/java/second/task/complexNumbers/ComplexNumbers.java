package second.task.complexNumbers;

public class ComplexNumbers {
    double my_real;
    double my_imag;

    public ComplexNumbers(double my_real, double my_imag){
        this.my_real = my_real;
        this.my_imag = my_imag;
    }

    public ComplexNumbers(double my_real){
        this.my_real = my_real;
        this.my_imag = 0.0;
    }

    @Override
    public String toString() {
        return my_real + " + " + my_imag + "i";
    }

    public  ComplexNumbers sum(ComplexNumbers n){
        double real = this.my_real + n.my_real;
        double imag = this.my_imag + n.my_imag;
        return new ComplexNumbers(real, imag);
    }

    public  ComplexNumbers subtraction(ComplexNumbers n) {
        double real = this.my_real - n.my_real;
        double imag = this.my_imag - n.my_imag;
        return new ComplexNumbers(real, imag);
    }
    public  ComplexNumbers multiplication(ComplexNumbers n) {
        double real = this.my_real * n.my_real - this.my_real * n.my_imag;
        double imag = this.my_imag * n.my_imag + this.my_imag * n.my_real;
        return new ComplexNumbers(real, imag );
    }
    public double abs() {
        return Math.sqrt(this.my_real*this.my_real + this.my_imag*this.my_imag);
    }
}
