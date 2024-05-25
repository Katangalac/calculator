package CalculatorModelisationTest;
import Calculator.Domain.CalculatorModelisation.EpressionManagement.ExpressionParser;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class ExpressionParserTest {
    @Test
    public void testParseExpression() {
        ExpressionParser parser = new ExpressionParser();


        List<String> tokens1 = parser.parseExpression("3 - 2");
        assertEquals(List.of("3", "-", "2"), tokens1);

        List<String> tokens2 = parser.parseExpression("log(4, 2) + 1");
        assertEquals(List.of("log", "(", "4", ",", "2", ")", "+", "1"), tokens2);

        List<String> tokens3 = parser.parseExpression("(3 * 2) / (4 + 1)");
        assertEquals(List.of("(", "3", "*", "2", ")", "/", "(", "4", "+", "1", ")"), tokens3);

        List<String> tokens4 = parser.parseExpression("1.5 + 2");
        assertEquals(List.of("1.5", "+", "2"), tokens4);

        List<String> tokens5 = parser.parseExpression("sin(5 - 4.5, 2) + 1");
        assertEquals(List.of("sin", "(", "5", "-", "4.5", ",", "2", ")", "+", "1"), tokens5);
    }
}
