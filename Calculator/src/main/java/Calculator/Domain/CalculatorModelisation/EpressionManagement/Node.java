package Calculator.Domain.CalculatorModelisation.EpressionManagement;

public class Node{
    char value;
    Node left;
    Node right;
    public Node(char value){
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