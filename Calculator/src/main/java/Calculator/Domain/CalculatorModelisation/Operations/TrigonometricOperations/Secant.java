package Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Secant implements UnaryOperation {
    public double calculate(double a){
        return 1.0 / Math.cos(Math.toRadians(a));
    }
}
