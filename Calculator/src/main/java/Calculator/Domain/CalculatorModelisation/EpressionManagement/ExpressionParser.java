package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ExpressionParser implements IExpressionParser {
    public List<String> parseExpression(String expression) {
        List<String> tokens = new ArrayList<>();

        String pattern = "(\\d+\\.?\\d*|-\\d+\\.?\\d*)" +
                         "|([\\+\\-*/(){}!,\\[\\]])" +
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
}