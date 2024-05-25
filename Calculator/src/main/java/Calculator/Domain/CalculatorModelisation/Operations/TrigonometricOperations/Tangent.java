package Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Tangent implements UnaryOperation {
    public double calculate(double a){
        return Math.tan(a);
    }
}
