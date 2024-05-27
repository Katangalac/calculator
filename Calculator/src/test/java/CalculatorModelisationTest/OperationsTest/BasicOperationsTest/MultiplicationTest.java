package CalculatorModelisationTest.OperationsTest.BasicOperationsTest;

import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Multiplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {

    @Test
    @DisplayName("Multiplication of positive numbers")
    void testMultiplicationOfPositiveNumbers() {
        Multiplication multiplication = new Multiplication();
        assertEquals(4, multiplication.calculate(2,2));
        assertEquals(220416, multiplication.calculate(328,672));
    }

    @Test
    @DisplayName("Multiplication of negative numbers")
    void testMultiplicationOfNegativeNumbers() {
        Multiplication multiplication = new Multiplication();
        assertEquals(16, multiplication.calculate(-8,-2));
        assertEquals(375, multiplication.calculate(-5,-75));
    }

    @Test
    @DisplayName("Multiplication of positive and Negative numbers")
    void testMultiplicationOfPositiveAndNegativeNumbers() {
        Multiplication multiplication = new Multiplication();
        assertEquals(-36, multiplication.calculate(-4,9));
        assertEquals(-217350, multiplication.calculate(-525,414));
    }

    @Test
    @DisplayName("Multiplication of floating numbers")
    void testMultiplicationOfFloatingNumbers() {
        Multiplication multiplication = new Multiplication();
        assertEquals(0.04, multiplication.calculate(0.1,0.4), 0.0001);
        assertEquals(36.12, multiplication.calculate(-4.2,-8.6), 0.0001);
        assertEquals(-9.69, multiplication.calculate(5.7,-1.7), 0.0001);
        assertEquals(-74574.85, multiplication.calculate(-1500.5,49.7), 0.0001);
    }

    @Test
    @DisplayName("Multiplication of Number with zero")
    void testMultiplicationOfNumberWithZero() {
        Multiplication multiplication = new Multiplication();
        assertEquals(0, multiplication.calculate(5,0));
        assertEquals(0, multiplication.calculate(0,-13));
        assertEquals(0, multiplication.calculate(45.8,0));
        assertEquals(0, multiplication.calculate(0,-8.5));
    }

    @Test
    @DisplayName("Multiplication of small floating number")
    void testMultiplicationOfSmallFloatingNumber() {
        Multiplication multiplication = new Multiplication();
        assertEquals(2e-20, multiplication.calculate(1e-10,2e-10), 0.0001);
        assertEquals(2e-20, multiplication.calculate(-1e-10,-2e-10), 0.0001);
        assertEquals(-2e-20, multiplication.calculate(-1e-10,2e-10),0.0001);
        assertEquals(-2e-20, multiplication.calculate(1e-10,-2e-10),0.0001);
    }
}