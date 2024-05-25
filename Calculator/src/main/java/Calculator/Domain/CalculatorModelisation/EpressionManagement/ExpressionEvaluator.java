package Calculator.Domain.CalculatorModelisation.EpressionManagement;
import Calculator.Domain.CalculatorModelisation.Operations.binaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

import java.util.Map;
public class ExpressionEvaluator {
    private IExpressionParser expressionParser;
    private Map<String, binaryOperation> binaryOperations;
    private Map<String, UnaryOperation> unaryOperations;
    public ExpressionEvaluator(IExpressionParser parser, Map<String, binaryOperation> bOperations, Map<String, UnaryOperation> uOperations){
        expressionParser = parser;
        binaryOperations = bOperations;
        unaryOperations = uOperations;
    }

    public double evaluate(String expression){return 0;}

    private boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("(") || op2.equals(")")) {
            return false;
        }
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        return true;
    }

}
