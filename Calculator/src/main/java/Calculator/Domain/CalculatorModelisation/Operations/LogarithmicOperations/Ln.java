package Calculator.Domain.CalculatorModelisation.Operations.LogarithmicOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

public class Ln implements UnaryOperation {
    @Override
    public double calculate(double a) {
        return Math.log(a);
    }
}
