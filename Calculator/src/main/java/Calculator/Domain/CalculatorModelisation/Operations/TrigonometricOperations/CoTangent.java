package Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations;

import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class CoTangent implements UnaryOperation {
    public double calculate(double a){
        return 1.0 / Math.tan(Math.toRadians(a));
    }
}
