package Calculator.Domain.Modelisation;

public class NthRoot implements Operation{
    public double calculate(double a, double n){
        if (a <= 0 || n <= 0) {
            throw new ArithmeticException("Base and root must be positive numbers.");
        }
        return Math.pow(a, 1.0 / n);
    }
}
