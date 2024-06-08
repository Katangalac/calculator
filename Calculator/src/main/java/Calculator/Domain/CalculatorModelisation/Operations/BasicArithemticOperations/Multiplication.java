package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;

public class Multiplication implements BinaryOperation {
    public double calculate(double a, double b){
        if(a == 0 || b == 0){
            return 0;
        }
        return a * b;
    }
}
