package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser implements IExpressionParser {
    public List<String> parseExpression(String expression) {

        if(!isValide(expression)){
            throw new IllegalArgumentException("Invalid expression");
        }

        List<String> tokens = new ArrayList<>();

        String pattern = "(\\d+\\.?\\d*|-\\d+\\.?\\d*)" +
                         "|([\\+\\-x/(){}!,\\[\\]])" +
                         "|(log|root|exp|exp10|sin|cos|tan|cotan|sec|cosec|neg|abs|ln)";

        Matcher matcher = Pattern.compile(pattern).matcher(expression);
        while (matcher.find()) {
            String token = matcher.group();
            if (token.startsWith("(") && token.endsWith(")")) {
                tokens.add(token.substring(1, token.length() - 1));
            } else {
                tokens.add(token);
            }
        }

        return tokens;
    }

    private boolean parenthesesMatcher(String expression) {
        Stack<Character> stack = new Stack<>();
        for(char c : expression.toCharArray()) {
            if(c == '('||c == '{'||c == '[') {
                stack.push(c);
            }
            else if(c == ')' || c == '}' || c == ']'){
                if(stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if((top == '(' && c != ')')||(top == '{' && c != '}') || (top == '[' && c != ']')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isValide(String expression) {

        if(!parenthesesMatcher(expression)){
            return false;
        }

        for (int i = 0; i < expression.length() - 1; i++) {
            char current = expression.charAt(i);
            char next = expression.charAt(i + 1);

            if (isOperator(current) && isOperator(next)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '%' || character == '!';
    }
}