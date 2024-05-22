package Calculator.Domain.Modelisation;

public class Tangent implements UnaryOperation{
    public double calculate(double a){
        return Math.tan(a);
    }
}
