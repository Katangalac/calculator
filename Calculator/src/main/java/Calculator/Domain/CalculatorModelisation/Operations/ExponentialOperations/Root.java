package Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Root implements BinaryOperation {
    public double calculate(double a, double n){
        if (a <= 0 || n <= 0) {
            throw new ArithmeticException("Base and root must be positive numbers.");
        }
        return Math.pow(a, 1.0 / n);
    }
}
