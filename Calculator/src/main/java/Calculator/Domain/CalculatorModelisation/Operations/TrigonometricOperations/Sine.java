package Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Sine implements UnaryOperation {
    public double calculate(double a){
        return Math.sin(Math.toRadians(a));
    }
}
