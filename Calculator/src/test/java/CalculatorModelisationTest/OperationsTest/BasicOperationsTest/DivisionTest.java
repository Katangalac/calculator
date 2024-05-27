package CalculatorModelisationTest.OperationsTest.BasicOperationsTest;

import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Addition;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Division;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    @Test
    @DisplayName("Division of positive numbers")
    void testDivisionPositiveNumbers() {
        Division division = new Division();
        assertEquals(1, division.calculate(2,2));
        assertEquals(0.4881, division.calculate(328,672), 0.0001);
    }

    @Test
    @DisplayName("Division of negative numbers")
    void testDivisionNegativeNumbers() {
        Division division = new Division();
        assertEquals(4, division.calculate(-8,-2));
        assertEquals(1.6667, division.calculate(-25,-15), 0.0001);
    }

    @Test
    @DisplayName("Division of positive and Negative numbers")
    void testDivisionPositiveAndNegativeNumbers() {
        Division division = new Division();
        assertEquals(-0.4444, division.calculate(-4,9), 0.0001);
        assertEquals(-1.2681, division.calculate(-525,414), 0.0001);
    }

    @Test
    @DisplayName("Division of floating numbers")
    void testDivisionFloatingNumbers() {
        Division division = new Division();
        assertEquals(0.25, division.calculate(0.1,0.4), 0.0001);
        assertEquals(0.4884, division.calculate(-4.2,-8.6), 0.0001);
        assertEquals(-3.3529, division.calculate(5.7,-1.7), 0.0001);
        assertEquals(-30.1911, division.calculate(-1500.5,49.7), 0.0001);
    }

    @Test
    @DisplayName("Division of Number by zero")
    void testAddNumberWithZero() {
        Division division = new Division();
        assertThrows(ArithmeticException.class, () -> division.calculate(5, 0));
        assertThrows(ArithmeticException.class, () -> division.calculate(45.8,0));
        assertEquals(0, division.calculate(0,-13));
        assertEquals(0, division.calculate(0,-8.5));
    }

    @Test
    @DisplayName("Division of small floating number")
    void testDivisionSmallFloatingNumber() {
        Division division = new Division();
        assertEquals(5e-6, division.calculate(1e-10,2e-5), 0.0001);
        assertEquals(3, division.calculate(-6e-10,-2e-10));
        assertEquals(-5e-1, division.calculate(-1e-10,2e-10));
        assertEquals(-8e5, division.calculate(24e-5,-3e-10));
    }
}