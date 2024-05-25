package Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations;

import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;

public class Exp10 implements binaryOperation {
    public double calculate(double a, double n){
        return a * Math.pow(10, n);
    }
}
