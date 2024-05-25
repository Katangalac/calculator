package Calculator.Domain.CalculatorModelisation.Operations.LogarithmicOperations;

import Calculator.Domain.CalculatorModelisation.Operations.MultipleTypeOperation;

public class Logarithm implements MultipleTypeOperation {
    public double calculate(double a){
        return Math.log(a);
    }
    public double calculate(double base, double value){
        return Math.log(value) / Math.log(base);
    }
}
