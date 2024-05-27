package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;

public class Multiplication implements binaryOperation {
    public double calculate(double a, double b){
        if(a == 0 || b == 0){
            return 0;
        }
        return a * b;
    }
}
