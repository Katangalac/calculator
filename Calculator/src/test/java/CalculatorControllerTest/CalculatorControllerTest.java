package CalculatorControllerTest;

import Calculator.Domain.CalculatorController.CalculatorController;
import Calculator.Domain.CalculatorModelisation.Calculator;
import Calculator.Domain.CalculatorModelisation.Converters.NumberConversionType;
import Calculator.Domain.CalculatorModelisation.Converters.NumberConverter;
import Calculator.Domain.CalculatorModelisation.Converters.UnitConversionType;
import Calculator.Domain.CalculatorModelisation.Converters.UnitConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    @Test
    @DisplayName("Evaluate a given expression")
    void testCalculate() {
        CalculatorController calculatorController = new CalculatorController();

        assertEquals(35, calculatorController.calculate("(3 + 4) x 5"));
        assertEquals(5, calculatorController.calculate("2 + 3"));
        assertEquals(23, calculatorController.calculate("3 + 4 x 5"));
        assertEquals(15, calculatorController.calculate("(2 + 3) x (4 - 1)"));
        assertEquals(15, calculatorController.calculate("(2 + 3) x 4 - 5"));
        assertEquals(1, calculatorController.calculate("-2 + 3"));
        assertEquals(-1.36, calculatorController.calculate("2.5 + -3.86"), 0.001);
        assertEquals(3.8, calculatorController.calculate("3 + 4 / 5"), 0.001);
        assertEquals(13, calculatorController.calculate("3 + 4 x 5 / 2"), 0.001);
        assertEquals(10, calculatorController.calculate("(2 + 3 x 5) + (4 - 2 x (5 + 4)) / (15 - 2 x 6.5)"));
    }

    @Test
    @DisplayName("Convert unit")
    void testConvertUnit() {
        CalculatorController calculatorController = new CalculatorController();

        assertEquals(Math.PI/4, calculatorController.convertUnit(UnitConversionType.DEGREES_TO_RADIANS, 45), 0.0001);
        assertEquals(45, calculatorController.convertUnit(UnitConversionType.RADIANS_TO_DEGREES, Math.PI / 4), 0.0001);
        assertEquals(50.0, calculatorController.convertUnit(UnitConversionType.DEGREES_TO_GRADIANS, 45), 0.0001);
        assertEquals(45.0, calculatorController.convertUnit(UnitConversionType.GRADIANS_TO_DEGREES, 50), 0.0001);
        assertEquals(50.0, calculatorController.convertUnit(UnitConversionType.RADIANS_TO_GRADIANS, Math.PI / 4), 0.0001);
        assertEquals(Math.PI / 4, calculatorController.convertUnit(UnitConversionType.GRADIANS_TO_RADIANS, 50), 0.0001);
    }

    @Test
    @DisplayName("Convert binary or hexadecimal number to decimal")
    void testConvertToDecimal() {
        CalculatorController calculatorController = new CalculatorController();

        assertEquals(5, calculatorController.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "101"));
        assertEquals(255, calculatorController.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "11111111"));
        assertEquals(0, calculatorController.convertToDecimal(NumberConversionType.BINARY_TO_DECIMAL, "0"));

        assertEquals(5, calculatorController.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "5"));
        assertEquals(255, calculatorController.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "FF"));
        assertEquals(0, calculatorController.convertToDecimal(NumberConversionType.HEXADECIMAL_TO_DECIMAL, "0"));
    }

    @Test
    @DisplayName("Convert decimal number to binary or hexadecimal number")
    void testConvertDecimal() {
        CalculatorController calculatorController = new CalculatorController();

        assertEquals("101", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 5));
        assertEquals("11111111", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 255));
        assertEquals("0", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_BINARY, 0));

        assertEquals("5", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 5));
        assertEquals("FF", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 255));
        assertEquals("0", calculatorController.convertDecimal(NumberConversionType.DECIMAL_TO_HEXADECIMAL, 0));
    }

    @Test
    @DisplayName("Convert non decimal number")
    void testConvertNonDecimalNumber() {
        CalculatorController calculatorController = new CalculatorController();

        assertEquals("5", calculatorController.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "101"));
        assertEquals("FF", calculatorController.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "11111111"));
        assertEquals("0", calculatorController.convertNonDecimalNumber(NumberConversionType.BINARY_TO_HEXADECIMAL, "0"));

        assertEquals("101", calculatorController.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "5"));
        assertEquals("11111111", calculatorController.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "FF"));
        assertEquals("0", calculatorController.convertNonDecimalNumber(NumberConversionType.HEXADECIMAL_TO_BINARY, "0"));
    }
}