package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionTree {
    private Map<String, BinaryOperation> binaryOperations = new HashMap<>();
    private Map<String, UnaryOperation> unaryOperations = new HashMap<>();
    private Map<Character, Integer> precedenceMap = new HashMap<>();

    public ExpressionTree() {
        precedenceMap.put('(', 1);
        precedenceMap.put('+', 2);
        precedenceMap.put('-', 2);
        precedenceMap.put('*', 3);
        precedenceMap.put('/', 3);
    }


    public Node buildTree(String expression){
        Stack<Character> operatorsStack = new Stack<>();
        Stack<Node> nodeStack = new Stack<>();

        for(char c : expression.toCharArray()){
            if(c == '('){
                operatorsStack.push(c);
            }else if(isNumber(c + "")){
                nodeStack.push(new Node(c));
            }else if(c == ')'){
                while(operatorsStack.peek() != '('){
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
        return nodeStack.peek();
    }

    private void merge(Stack<Character> ops, Stack<Node> nodes){
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
