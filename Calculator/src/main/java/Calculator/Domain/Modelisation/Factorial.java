package Calculator.Domain.Modelisation;

public class Factorial implements UnaryOperation{
    public double calculate(double a){
        if (a < 0){
            throw new ArithmeticException("Factorial of a negative number is undefined.");
        }

        if (a == 0){
            return 1;
        }

        else if (a == 1){
            return 1;
        }

        else {
            return a * calculate(a - 1);
        }

    }
}
