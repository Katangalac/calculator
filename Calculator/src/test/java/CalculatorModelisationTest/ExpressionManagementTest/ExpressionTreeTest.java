package CalculatorModelisationTest.ExpressionManagementTest;

import Calculator.Domain.CalculatorModelisation.EpressionManagement.ExpressionTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {

    @Test
    @DisplayName("Test of building tree")
    void buildTree() {
        ExpressionTree tree = new ExpressionTree();

        tree.buildTree("(3 + 4) * 5");
        assertEquals("((3+4)*5)", tree.getExpression());

        tree.buildTree("2 + 3");
        assertEquals("(2+3)", tree.getExpression());

        tree.buildTree("(2 + 3) * (4 - 1)");
        assertEquals("((2+3)*(4-1))", tree.getExpression());

        tree.buildTree("(2 + 3) * 4 - 5");
        assertEquals("(((2+3)*4)-5)", tree.getExpression());

        tree.buildTree("-2 + 3");
        assertEquals("(-2+3)", tree.getExpression());

        tree.buildTree("2.5 + -3.86");
        assertEquals("(2.5+-3.86)", tree.getExpression());
    }
}