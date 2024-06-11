package CalculatorModelisationTest.ConvertersTest;

import Calculator.Domain.CalculatorModelisation.Converters.NumberConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {

    @Test
    @DisplayName("Convert decimal number to binary")
    void testDecimalToBinary() {
        NumberConverter converter = new NumberConverter();
        assertEquals("101", converter.decimalToBinary(5));
        assertEquals("11111111", converter.decimalToBinary(255));
        assertEquals("0", converter.decimalToBinary(0));
    }

    @Test
    @DisplayName("Convert binary number to decimal")
    void testBinaryToDecimal() {
        NumberConverter converter = new NumberConverter();
        assertEquals(5, converter.binaryToDecimal("101"));
        assertEquals(255, converter.binaryToDecimal("11111111"));
        assertEquals(0, converter.binaryToDecimal("0"));
    }

    @Test
    @DisplayName("Convert decimal number to hexadecimal")
    void testDecimalToHexadecimal() {
        NumberConverter converter = new NumberConverter();
        assertEquals("5", converter.decimalToHexadecimal(5));
        assertEquals("FF", converter.decimalToHexadecimal(255));
        assertEquals("0", converter.decimalToHexadecimal(0));
    }

    @Test
    @DisplayName("Convert hexadecimal number to decimal")
    void testHexadecimalToDecimal() {
        NumberConverter converter = new NumberConverter();
        assertEquals(5, converter.hexadecimalToDecimal("5"));
        assertEquals(255, converter.hexadecimalToDecimal("FF"));
        assertEquals(0, converter.hexadecimalToDecimal("0"));
    }

    @Test
    @DisplayName("convert binary number to hexadecimal")
    void testBinaryToHexadecimal() {
        NumberConverter converter = new NumberConverter();
        assertEquals("5", converter.binaryToHexadecimal("101"));
        assertEquals("FF", converter.binaryToHexadecimal("11111111"));
        assertEquals("0", converter.binaryToHexadecimal("0"));
    }

    @Test
    @DisplayName("convert hexadecimal number to binary")
    void testHexadecimalToBinary() {
        NumberConverter converter = new NumberConverter();
        assertEquals("101", converter.hexadecimalToBinary("5"));
        assertEquals("11111111", converter.hexadecimalToBinary("FF"));
        assertEquals("0", converter.hexadecimalToBinary("0"));
    }
}