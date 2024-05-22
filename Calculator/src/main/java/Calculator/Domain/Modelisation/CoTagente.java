package Calculator.Domain.Modelisation;

public class CoTagente implements UnaryOperation{
    public double calculate(double a){
        return 1.0 / Math.tan(a);
    }
}
