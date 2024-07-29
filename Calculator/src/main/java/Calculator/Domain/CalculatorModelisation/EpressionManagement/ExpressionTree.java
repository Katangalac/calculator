package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionTree implements IExpressionTree{

    private static class Node {
        String value;
        Node left;
        Node right;
        public Node(String value){
            this.value = value;
        }

        public String toString(){
            return toInfixString(this);
        }

        private String toInfixString(Node node) {
            if (node == null) {
                return "";
            } else if (node.left == null && node.right == null) {
                return String.valueOf(node.value);
            } else {
                String leftString = toInfixString(node.left);
                String rightString = toInfixString(node.right);
                return "(" + leftString + node.value + rightString + ")";
            }
        }
    }

    private Node root;
    private final Map<String, BinaryOperation> binaryOperations;
    private final Map<String, UnaryOperation> unaryOperations;
    private final Map<String, Integer> precedenceMap = new HashMap<>();

    public ExpressionTree(Map<String, BinaryOperation> bOperations, Map<String, UnaryOperation> uOperations) {
        root = null;
        binaryOperations = bOperations;
        unaryOperations = uOperations;
        precedenceMap.put("(", 1);
        precedenceMap.put("+", 2);
        precedenceMap.put("-", 2);
        precedenceMap.put("x", 3);
        precedenceMap.put("/", 3);
    }

    public String getExpression() {
        return root != null ? root.toString() : "";
    }

    public void buildTree(String expression){
        Stack<String> operatorsStack = new Stack<>();
        Stack<Node> nodeStack = new Stack<>();
        ExpressionParser parser = new ExpressionParser();
        List<String> tokens = parser.parseExpression(expression);

        for(String c : tokens){
            if(c.equals("(")){
                operatorsStack.push(c);
            }else if(isNumber(c)){
                nodeStack.push(new Node(c));
            }else if(c.equals(")")){
                while(!operatorsStack.peek().equals("(")){
                    merge(operatorsStack, nodeStack);
                }
                operatorsStack.pop();
            }else{
                while(!operatorsStack.isEmpty() && precedenceMap.get(operatorsStack.peek()) >= precedenceMap.get(c)){
                    merge(operatorsStack, nodeStack);
                }
                operatorsStack.push(c);
            }
        }

        while(nodeStack.size() > 1){
            merge(operatorsStack, nodeStack);
        }
        root = nodeStack.peek();
    }

    private void merge(Stack<String> ops, Stack<Node> nodes){
        Node root = new Node(ops.pop());
        root.right = nodes.pop();
        root.left = nodes.pop();
        nodes.push(root);
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

    public double evaluate() {
        return evaluateTree(root);
    }

    private double evaluateTree(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is null");
        }

        if(node.left == null && node.right == null){
            return Double.parseDouble(node.value);
        }

        if(node.left != null && node.right != null){
            if(isBinaryOperator(node.value)){
                BinaryOperation op = binaryOperations.get(node.value);
                double leftEval = evaluateTree(node.left);
                double rightEval = evaluateTree(node.right);
                return op.calculate(leftEval, rightEval);
            }else{
                throw new UnsupportedOperationException("Unsupported binary operator " + node.value);
            }
        }

        if(node.left != null && node.right == null){
            if(isUnaryOperator(node.value)){
                UnaryOperation op = unaryOperations.get(node.value);
                double leftEval = evaluateTree(node.right);
                return op.calculate(leftEval);
            }else{
                throw new UnsupportedOperationException("Unsupported unary operator " + node.value);
            }
        }else if(node.left == null && node.right != null){
            if(isUnaryOperator(node.value)){
                UnaryOperation op = unaryOperations.get(node.value);
                double rightEval = evaluateTree(node.right);
                return op.calculate(rightEval);
            }else{
                throw new UnsupportedOperationException("Unsupported unary operator " + node.value);
            }
        }

        throw new IllegalStateException("Unexpected state in expression tree at node: " + node.value);
    }
}
