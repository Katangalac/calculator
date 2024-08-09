package Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Cosine implements UnaryOperation {
    public double calculate(double a){
        return Math.cos(Math.toRadians(a));
    }
}
