package Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations;

import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;

public class Division implements binaryOperation {
    public double calculate(double a, double b){
        if(b==0){
            throw new ArithmeticException("Division by zero");
        }
        else if(a == 0){
            return 0;
        }
        return a / b;
    }
}
