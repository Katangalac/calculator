package Calculator.Domain.CalculatorModelisation.EpressionManagement;

public interface IExpressionTree {
    void buildTree(String expression);
    double evaluate();
}
