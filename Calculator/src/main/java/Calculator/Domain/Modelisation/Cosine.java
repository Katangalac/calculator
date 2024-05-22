package Calculator.Domain.Modelisation;

public class Cosine implements UnaryOperation{
    public double calculate(double a){
        return Math.cos(a);
    }
}
