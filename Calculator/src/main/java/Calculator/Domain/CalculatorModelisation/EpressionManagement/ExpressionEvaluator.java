package Calculator.Domain.CalculatorModelisation.EpressionManagement;
import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.MultiTypeOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluator {
    private IExpressionParser expressionParser;
    private Map<String, BinaryOperation> binaryOperations;
    private Map<String, UnaryOperation> unaryOperations;
    private Map<String, MultiTypeOperation> multiTypeOperations;

    //The evaluator must follow the PEMDAS rule

    public ExpressionEvaluator(IExpressionParser parser, Map<String, BinaryOperation> bOperations, Map<String, UnaryOperation> uOperations, Map<String, MultiTypeOperation> mOperations){
        expressionParser = parser;
        binaryOperations = bOperations;
        unaryOperations = uOperations;
        multiTypeOperations = mOperations;
    }

    public double evaluate(String expression){
        List<String> tokens = expressionParser.parseExpression(expression);
        Stack<String> operationsStack = new Stack<>();
        Stack<String> valuesStack = new Stack<>();
        return 0;
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isUnaryOperator(String token) {
        return unaryOperations.containsKey(token);
    }

    private boolean isBinaryOperator(String token) {
        return binaryOperations.containsKey(token);
    }

    private boolean isMultiTypeOperator(String token) {
        return multiTypeOperations.containsKey(token);
    }

    private int findClosingParenthesis(List<String> tokens, int openingIndex) {
        int depth = 1;
        for (int i = openingIndex + 1; i < tokens.size(); i++) {
            if (tokens.get(i).equals("(")) {
                depth++;
            } else if (tokens.get(i).equals(")")) {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException("No closing parenthesis found");
    }

    private double applyOperation(String op, Stack<Double> values) {
        if (isBinaryOperator(op)) {
            double b = values.pop();
            double a = values.pop();
            return binaryOperations.get(op).calculate(a, b);
        } else if (isUnaryOperator(op)) {
            double a = values.pop();
            return unaryOperations.get(op).calculate(a);
        } else if (isMultiTypeOperator(op)) {
            double a = values.pop();
            if (!values.isEmpty() && !isBinaryOperator(op)) {
                double b = values.pop();
                return multiTypeOperations.get(op).calculate(a, b);
            }
            return multiTypeOperations.get(op).calculate(a);
        }
        throw new UnsupportedOperationException("Invalid operation: " + op);
    }
}
