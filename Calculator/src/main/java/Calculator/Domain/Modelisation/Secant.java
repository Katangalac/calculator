package Calculator.Domain.Modelisation;

public class Secant implements UnaryOperation{
    public double calculate(double a){
        return 1.0 / Math.cos(a);
    }
}
