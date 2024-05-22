package Calculator.Domain.Modelisation;

public class Exponentiation implements Operation{
    public double calculate(double base, double exponent){
        return Math.pow(base, exponent);
    }
}
