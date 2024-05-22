package Calculator.Domain.Modelisation;

public class Logarithm implements Operation{
    public double calculate(double base, double value){
        return Math.log(value) / Math.log(base);
    }
}
