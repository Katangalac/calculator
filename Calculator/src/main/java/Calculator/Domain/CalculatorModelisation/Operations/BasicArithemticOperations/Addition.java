package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;

public class Addition implements binaryOperation {
    public double calculate(double a, double b){
        return a + b;
    }
}
