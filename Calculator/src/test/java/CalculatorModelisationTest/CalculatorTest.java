package CalculatorModelisationTest;

import Calculator.Domain.CalculatorModelisation.Calculator;
import Calculator.Domain.CalculatorModelisation.Converters.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("Evaluate a given expression")
    void testCalculate() {
        Calculator calculator = new Calculator(new NumberConverter(), new UnitConverter());

        assertEquals(35, calculator.calculate("(3 + 4) x 5"));
        assertEquals(5, calculator.calculate("2 + 3"));
        assertEquals(23, calculator.calculate("3 + 4 x 5"));
        assertEquals(15, calculator.calculate("(2 + 3) x (4 - 1)"));
        assertEquals(15, calculator.calculate("(2 + 3) x 4 - 5"));
        assertEquals(1, calculator.calculate("-2 + 3"));
        assertEquals(-1.36, calculator.calculate("2.5 + -3.86"), 0.001);
        assertEquals(3.8, calculator.calculate("3 + 4 / 5"), 0.001);
        assertEquals(13, calculator.calculate("3 + 4 x 5 / 2"), 0.001);
        assertEquals(10, calculator.calculate("(2 + 3 x 5) + (4 - 2 x (5 + 4)) / (15 - 2 x 6.5)"));
    }

    @Test
    @DisplayName("Convert unit")
    void testConvertUnit() {
        Calculator calculator = new Calculator(new NumberConverter(), new UnitConverter());

        assertEquals(Math.PI/4, calculator.convertUnit(UnitConversionType.DEGREES_TO_RADIANS, 45), 0.0001);
        assertEquals(45, calculator.convertUnit(UnitConversionType.RADIANS_TO_DEGREES, Math.PI / 4), 0.0001);
        assertEquals(50.0, calculator.convertUnit(UnitConversionType.DEGREES_TO_GRADIANS, 45), 0.0001);
        assertEquals(45.0, calculator.convertUnit(UnitConversionType.GRADIANS_TO_DEGREES, 50), 0.0001);
        assertEquals(50.0, calculator.convertUnit(UnitConversionType.RADIANS_TO_GRADIANS, Math.PI / 4), 0.0001);
        assertEquals(Math.PI / 4, calculator.convertUnit(UnitConversionType.GRADIANS_TO_RADIANS, 50), 0.0001);
    }

    @Test
    @DisplayName("Convert binary or hexadecimal number to decimal")
    void testConvertToDecimal() {
        Calculator calculator = new Calculator(new NumberConverter(), new UnitConverter());

        assertEquals(5, calculator.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "101"));
        assertEquals(255, calculator.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "11111111"));
        assertEquals(0, calculator.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "0"));

        assertEquals(5, calculator.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "5"));
        assertEquals(255, calculator.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "FF"));
        assertEquals(0, calculator.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "0"));
    }

    @Test
    @DisplayName("Convert decimal number to binary or hexadecimal number")
    void testConvertDecimal() {
        Calculator calculator = new Calculator(new NumberConverter(), new UnitConverter());

        assertEquals("101", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 5));
        assertEquals("11111111", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 255));
        assertEquals("0", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 0));

        assertEquals("5", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 5));
        assertEquals("FF", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 255));
        assertEquals("0", calculator.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 0));
    }

    @Test
    @DisplayName("Convert non decimal number")
    void testConvertNonDecimalNumber() {
        Calculator calculator = new Calculator(new NumberConverter(), new UnitConverter());

        assertEquals("5", calculator.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "101"));
        assertEquals("FF", calculator.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "11111111"));
        assertEquals("0", calculator.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "0"));

        assertEquals("101", calculator.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "5"));
        assertEquals("11111111", calculator.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "FF"));
        assertEquals("0", calculator.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "0"));
    }
}