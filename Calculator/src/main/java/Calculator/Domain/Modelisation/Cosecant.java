package Calculator.Domain.Modelisation;

public class Cosecant implements UnaryOperation{
    public double calculate(double a){
        return 1.0 / Math.sin(a);
    }
}
