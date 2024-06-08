package Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Exponentiation implements BinaryOperation {
    public double calculate(double base, double exponent){
        return Math.pow(base, exponent);
    }
}
