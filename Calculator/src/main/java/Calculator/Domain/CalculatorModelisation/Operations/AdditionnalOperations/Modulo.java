package Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Modulo implements BinaryOperation {
    public double calculate(double a, double b) {
        if (b == 0){
            throw new ArithmeticException("Division by zero");
        }
        return a % b;
    }
}
