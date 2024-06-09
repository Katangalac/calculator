package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionTree {

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
    private Map<String, BinaryOperation> binaryOperations = new HashMap<>();
    private Map<String, UnaryOperation> unaryOperations = new HashMap<>();
    private Map<String, Integer> precedenceMap = new HashMap<>();

    public ExpressionTree() {
        root = null;
        precedenceMap.put("(", 1);
        precedenceMap.put("+", 2);
        precedenceMap.put("-", 2);
        precedenceMap.put("*", 3);
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
        System.out.println(tokens);
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
}
