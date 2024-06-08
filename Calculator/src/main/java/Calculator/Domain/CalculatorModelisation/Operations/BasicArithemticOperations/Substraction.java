package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Substraction implements BinaryOperation {
    public double calculate(double a, double b){
        return a - b;
    }
}
