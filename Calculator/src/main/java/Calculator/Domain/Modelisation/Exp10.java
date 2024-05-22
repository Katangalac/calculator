package Calculator.Domain.Modelisation;

public class Exp10 implements Operation{
    public double calculate(double a, double n){
        return a * Math.pow(10, n);
    }
}
