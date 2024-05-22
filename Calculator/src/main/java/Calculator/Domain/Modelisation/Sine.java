package Calculator.Domain.Modelisation;

public class Sine implements UnaryOperation{
    public double calculate(double a){
        return Math.sin(a);
    }
}
