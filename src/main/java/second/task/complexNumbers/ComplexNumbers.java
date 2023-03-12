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
        ComplexNumbers temp = new ComplexNumbers(0.0, 0.0);
        temp.my_real = this.my_real + n.my_real;
        temp.my_imag = this.my_imag + n.my_imag;
        return(temp);
    }

    public  ComplexNumbers subtraction(ComplexNumbers n) {
        ComplexNumbers temp = new ComplexNumbers(0.0, 0.0);
        temp.my_real = this.my_real - n.my_real;
        temp.my_imag = this.my_imag - n.my_imag;
        return(temp);
    }
    public  ComplexNumbers multiplication(ComplexNumbers n) {
        ComplexNumbers temp = new ComplexNumbers(0.0, 0.0);
        temp.my_real = this.my_real * n.my_real - this.my_real * n.my_imag;
        temp.my_imag = this.my_imag * n.my_imag + this.my_imag * n.my_real;
        return(temp);
    }
    public double abs() {
        return Math.sqrt(this.my_real*this.my_real + this.my_imag*this.my_imag);
    }
}
