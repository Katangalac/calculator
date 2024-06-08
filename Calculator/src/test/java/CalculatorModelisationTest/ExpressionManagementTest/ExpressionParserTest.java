package CalculatorModelisationTest.ExpressionManagementTest;

import Calculator.Domain.CalculatorModelisation.EpressionManagement.ExpressionParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    @DisplayName("Parse expression with two operands")
    void testParseExpressionWithTwoOperands() {
        ExpressionParser parser = new ExpressionParser();

        List<String> tokens1 = parser.parseExpression("3 - 2");
        assertEquals(List.of("3", "-", "2"), tokens1);

        List<String> tokens2 = parser.parseExpression("1.5 + 2");
        assertEquals(List.of("1.5", "+", "2"), tokens2);

    }

    @Test
    @DisplayName("Parse complex expression")
    void testParseComplexExpression() {
        ExpressionParser parser = new ExpressionParser();

        List<String> tokens1 = parser.parseExpression("(3 * 2) / (4 + 1)");
        assertEquals(List.of("(", "3", "*", "2", ")", "/", "(", "4", "+", "1", ")"), tokens1);

        List<String> tokens2 = parser.parseExpression("5 - 4 * (5! + 1)");
        assertEquals(List.of("5", "-", "4", "*", "(", "5", "!", "+", "1", ")"), tokens2);
    }

    @Test
    @DisplayName("Parse expression with functions")
    void testParseExpressionWithFunctions() {
        ExpressionParser parser = new ExpressionParser();

        List<String> tokens1 = parser.parseExpression("log(4, 2) + 1");
        assertEquals(List.of("log", "(", "4", ",", "2", ")", "+", "1"), tokens1);

        List<String> tokens2 = parser.parseExpression("sin(30) + 20 - exp(2, 3)");
        assertEquals(List.of("sin", "(", "30", ")", "+", "20", "-", "exp", "(", "2", ",", "3", ")"), tokens2);
    }

    @Test
    @DisplayName("Parse expression with multiple type of parentheses")
    void testParseExpressionWithMultipleTypeOfParentheses() {
        ExpressionParser parser = new ExpressionParser();

        List<String> tokens1 = parser.parseExpression("3 * [4 + {5 - 2}]");
        assertEquals(List.of("3", "*", "[", "4", "+", "{", "5", "-", "2", "}", "]"), tokens1);

        List<String> tokens2 = parser.parseExpression("{2 * (6 - [3 / 2])}");
        assertEquals(List.of("{", "2", "*", "(", "6", "-", "[", "3", "/", "2", "]", ")", "}"), tokens2);

        List<String> tokens3 = parser.parseExpression("(8 / {2 * [4 - (3 + 1)]})");
        assertEquals(List.of("(", "8", "/", "{", "2", "*", "[", "4", "-", "(", "3", "+", "1", ")", "]", "}", ")"), tokens3);

    }

    @Test
    @DisplayName("Parse expression with invalide parentheses")
    void testParseExpressionWithInvalideParentheses() {
        ExpressionParser parser = new ExpressionParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("{2 * (6 - [3 / 2])})"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("(8 / {2 * [(4 - (3 + 1)]})"));
    }

    @Test
    @DisplayName("Parse expression with invalide brackets")
    void testParseExpressionWithInvalideBrackets() {
        ExpressionParser parser = new ExpressionParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("{2 * (6 - [3 / 2]])})"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("[(8 / {2 * [4 - (3 + 1)]})"));
    }

    @Test
    @DisplayName("Parse expression with invalide braces")
    void testParseExpressionWithInvalideBraces() {
        ExpressionParser parser = new ExpressionParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("{2 * ({6 - [3 / 2])})"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("(8 / {2 * [4 - (3 + 1)]})}"));
    }

    @Test
    @DisplayName("Parse expression with consecutive operator")
    void testParseExpressionWithConsecutiveOperator() {
        ExpressionParser parser = new ExpressionParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("{2 * ({6 -* [3 / 2])})"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("(8 / {2 * [4 - (3 +- 1)]})}"));
        assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("[(2 * ({6 * [3 / 2])) /+ (8 / {2 * [4 - (3 + 1)]})]"));
    }

}