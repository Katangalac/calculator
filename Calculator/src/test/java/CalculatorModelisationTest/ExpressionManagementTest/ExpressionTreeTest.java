package CalculatorModelisationTest.ExpressionManagementTest;

import Calculator.Domain.CalculatorModelisation.EpressionManagement.ExpressionTree;
import Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations.Negative;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Addition;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Division;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Multiplication;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Substraction;
import Calculator.Domain.CalculatorModelisation.Operations.BinaryOperation;
import Calculator.Domain.CalculatorModelisation.Operations.OperationsInitializer;
import Calculator.Domain.CalculatorModelisation.Operations.UnaryOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {

    @Test
    @DisplayName("Test of building tree")
    void testBuildTree() {
        Map<String, BinaryOperation> bOperations = OperationsInitializer.initializeBinaryOperations();
        Map<String, UnaryOperation> uOperations = OperationsInitializer.initializeUnaryOperations();

        ExpressionTree tree = new ExpressionTree(bOperations, uOperations);

        tree.buildTree("(3 + 4) x 5");
        assertEquals("((3+4)x5)", tree.getExpression());

        tree.buildTree("2 + 3");
        assertEquals("(2+3)", tree.getExpression());

        tree.buildTree("3 + 4 x 5");
        assertEquals("(3+(4x5))", tree.getExpression());

        tree.buildTree("(2 + 3) x (4 - 1)");
        assertEquals("((2+3)x(4-1))", tree.getExpression());

        tree.buildTree("(2 + 3) x 4 - 5");
        assertEquals("(((2+3)x4)-5)", tree.getExpression());

        tree.buildTree("-2 + 3");
        assertEquals("(-2+3)", tree.getExpression());

        tree.buildTree("2.5 + -3.86");
        assertEquals("(2.5+-3.86)", tree.getExpression());

    }

    @Test
    @DisplayName("Evaluate the expression tree")
    void testEvaluate() {
        Map<String, BinaryOperation> bOperations = new HashMap<>();
        bOperations.put("+", new Addition());
        bOperations.put("-", new Substraction());
        bOperations.put("x", new Multiplication());
        bOperations.put("/", new Division());

        Map<String, UnaryOperation> uOperations = new HashMap<>();
        uOperations.put("-", new Negative());

        ExpressionTree tree = new ExpressionTree(bOperations, uOperations);

        tree.buildTree("(3 + 4) x 5");
        assertEquals(35, tree.evaluate());

        tree.buildTree("2 + 3");
        assertEquals(5, tree.evaluate());

        tree.buildTree("3 + 4 x 5");
        assertEquals(23, tree.evaluate());

        tree.buildTree("(2 + 3) x (4 - 1)");
        assertEquals(15, tree.evaluate());

        tree.buildTree("(2 + 3) x 4 - 5");
        assertEquals(15, tree.evaluate());

        tree.buildTree("-2 + 3");
        assertEquals(1, tree.evaluate());

        tree.buildTree("2.5 + -3.86");
        assertEquals(-1.36, tree.evaluate(), 0.001);

        tree.buildTree("3 + 4 / 5");
        assertEquals(3.8, tree.evaluate(), 0.001);

        tree.buildTree("3 + 4 x 5 / 2");
        assertEquals(13, tree.evaluate(), 0.001);

        tree.buildTree("(2 + 3 x 5) + (4 - 2 x (5 + 4)) / (15 - 2 x 6.5)");
        assertEquals(10, tree.evaluate());

        //tree.buildTree("- (2 + 3)");
        //assertEquals(-5, tree.evaluate());
    }
}