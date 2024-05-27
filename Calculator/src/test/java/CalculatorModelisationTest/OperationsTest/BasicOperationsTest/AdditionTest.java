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
        assertEquals(1000, addition.calculate(328,672));
    }

    @Test
    @DisplayName("Addition of negative numbers")
    void testAddNegativeNumbers() {
        Addition addition = new Addition();
        assertEquals(-10, addition.calculate(-8,-2));
        assertEquals(-1500, addition.calculate(-750,-750));
    }

    @Test
    @DisplayName("Addition of positive and Negative numbers")
    void testAddPositiveAndNegativeNumbers() {
        Addition addition = new Addition();
        assertEquals(5, addition.calculate(-4,9));
        assertEquals(-111, addition.calculate(-525,414));
    }

    @Test
    @DisplayName("Addition of floating numbers")
    void testAddFloatingNumbers() {
        Addition addition = new Addition();
        assertEquals(0.5, addition.calculate(0.1,0.4));
        assertEquals(-12.8, addition.calculate(-4.2,-8.6));
        assertEquals(4.0, addition.calculate(5.7,-1.7));
        assertEquals(-1450.8, addition.calculate(-1500.5,49.7));
    }

    @Test
    @DisplayName("Addition of Number with zero")
    void testAddNumberWithZero() {
        Addition addition = new Addition();
        assertEquals(5, addition.calculate(5,0));
        assertEquals(-13, addition.calculate(0,-13));
        assertEquals(45.8, addition.calculate(45.8,0));
        assertEquals(-8.5, addition.calculate(0,-8.5));
    }

    @Test
    @DisplayName("Addition of small floating number")
    void testAddSmallFloatingNumber() {
        Addition addition = new Addition();
        assertEquals(3e-10, addition.calculate(1e-10,2e-10));
        assertEquals(-3e-10, addition.calculate(-1e-10,-2e-10));
        assertEquals(1e-10, addition.calculate(-1e-10,2e-10));
        assertEquals(-1e-10, addition.calculate(1e-10,-2e-10));
    }
}