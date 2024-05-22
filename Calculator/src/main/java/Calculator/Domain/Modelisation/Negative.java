package Calculator.Domain.Modelisation;

public class Negative implements UnaryOperation{
    public double calculate(double a){
        return -a;
    }
}
