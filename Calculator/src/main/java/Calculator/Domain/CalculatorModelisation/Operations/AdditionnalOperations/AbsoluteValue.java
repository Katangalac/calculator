package Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class AbsoluteValue implements UnaryOperation {
    public double calculate(double a){
        return Math.abs(a);
    }
}
