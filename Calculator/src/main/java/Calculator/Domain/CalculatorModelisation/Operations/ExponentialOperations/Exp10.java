package Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Exp10 implements BinaryOperation {
    public double calculate(double a, double n){
        return a * Math.pow(10, n);
    }
}
