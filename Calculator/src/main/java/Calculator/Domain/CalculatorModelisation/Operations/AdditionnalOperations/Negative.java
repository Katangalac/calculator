package Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Negative implements UnaryOperation {
    public double calculate(double a){
        return -a;
    }
}
