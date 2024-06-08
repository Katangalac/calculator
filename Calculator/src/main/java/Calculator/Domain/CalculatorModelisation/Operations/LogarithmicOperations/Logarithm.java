package Calculator.Domain.CalculatorModelisation.Operations.LogarithmicOperations;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.MultiTypeOperation;

public class Logarithm implements BinaryOperation {
    public double calculate(double base, double value){
        return Math.log(value) / Math.log(base);
    }
}
