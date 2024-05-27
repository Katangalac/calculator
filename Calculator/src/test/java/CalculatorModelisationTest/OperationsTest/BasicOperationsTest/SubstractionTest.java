package CalculatorModelisationTest.OperationsTest.BasicOperationsTest;

import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.Substraction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstractionTest {

    @Test
    @DisplayName("Substraction of positive numbers")
    void testAddPositiveNumbers() {
        Substraction substraction = new Substraction();
        assertEquals(0, substraction.calculate(2,2));
        assertEquals(-36, substraction.calculate(12,48));
        assertEquals(250, substraction.calculate(1000,750));
    }

    @Test
    @DisplayName("Substraction of negative numbers")
    void testAddNegativeNumbers() {
        Substraction substraction = new Substraction();
        assertEquals(0, substraction.calculate(-2,-2));
        assertEquals(-100, substraction.calculate(-250,-150));
        assertEquals(85, substraction.calculate(-260,-345));
    }

    @Test
    @DisplayName("Substraction of positive and Negative numbers")
    void testAddPositiveAndNegativeNumbers() {
        Substraction substraction = new Substraction();
        assertEquals(-13, substraction.calculate(-4,9));
        assertEquals(-939, substraction.calculate(-525,414));
    }

    @Test
    @DisplayName("Substraction of floating numbers")
    void testAddFloatingNumbers() {
        Substraction substraction = new Substraction();
        assertEquals(-0.3, substraction.calculate(0.1,0.4), 0.0001);
        assertEquals(4.4, substraction.calculate(-4.2,-8.6), 0.0001);
        assertEquals(7.4, substraction.calculate(5.7,-1.7), 0.0001);
        assertEquals(-1550.2, substraction.calculate(-1500.5,49.7), 0.0001);
    }

    @Test
    @DisplayName("Substraction of Number with zero")
    void testAddNumberWithZero() {
        Substraction substraction = new Substraction();
        assertEquals(5, substraction.calculate(5,0));
        assertEquals(13, substraction.calculate(0,-13));
        assertEquals(45.8, substraction.calculate(45.8,0));
        assertEquals(8.5, substraction.calculate(0,-8.5));
    }

    @Test
    @DisplayName("Substraction of small floating number")
    void testAddSmallFloatingNumber() {
        Substraction substraction = new Substraction();
        assertEquals(-1e-10, substraction.calculate(1e-10,2e-10));
        assertEquals(1e-10, substraction.calculate(-1e-10,-2e-10));
        assertEquals(-3e-10, substraction.calculate(-1e-10,2e-10));
        assertEquals(3e-10, substraction.calculate(1e-10,-2e-10));
    }
}