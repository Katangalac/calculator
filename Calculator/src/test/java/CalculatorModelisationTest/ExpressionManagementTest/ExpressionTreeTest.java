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
        assertEquals("((3+4)*5)", tree.buildTree("(3+4)*5").toString());
        assertEquals("(2+3)", tree.buildTree("2+3").toString());
        assertEquals("((2+3)*(4-1))", tree.buildTree("(2+3)*(4-1)").toString());
        assertEquals("(((2+3)*4)-5)", tree.buildTree("(2+3)*4-5").toString());
    }
}