package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Addition implements BinaryOperation {
    public double calculate(double a, double b){
        return a + b;
    }
}
