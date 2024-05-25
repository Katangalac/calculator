package Calculator.Domain.CalculatorModelisation.EpressionManagement;

import java.util.List;

public interface IExpressionParser {
    List<String> parseExpression(String expression);
}
