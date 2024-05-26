package CalculatorModelisationTest.OperationsTest.BasicOperationsTest;

import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Addition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    @DisplayName("Addition of positive numbers")
    void testAddPositiveNumbers() {
        Addition addition = new Addition();
        assertEquals(4, addition.calculate(2,2));
        assertEquals(1000, addition.calculate(500,500));
        assertEquals(15, addition.calculate(1,14));
    }
}