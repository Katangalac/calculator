package Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations;

import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;

public class Exponentiation implements binaryOperation {
    public double calculate(double base, double exponent){
        return Math.pow(base, exponent);
    }
}
