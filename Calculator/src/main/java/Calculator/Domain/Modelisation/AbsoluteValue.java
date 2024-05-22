package Calculator.Domain.Modelisation;

public class AbsoluteValue implements UnaryOperation{
    public double calculate(double a){
        return Math.abs(a);
    }
}
