package Calculator.Domain.CalculatorModelisation.Converters;

import Calculator.Domain.CalculatorModelisation.Converters.INumberConverter;

public class NumberConverter implements INumberConverter {
    public String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    public int binaryToDecimal(String binaire) {
        return Integer.parseInt(binaire, 2);
    }

    public String decimalToHexadecimal(int decimal) {
        return Integer.toHexString(decimal);
    }

    public int hexadecimalToDecimal(String hexadecimal) {
        return Integer.parseInt(hexadecimal, 16);
    }

    public String binaryToHexadecimal(String binaire) {
        int decimal = binaryToDecimal(binaire);
        return decimalToHexadecimal(decimal);
    }

    public String hexadecimalToBinary(String hexadecimal) {
        int decimal = hexadecimalToDecimal(hexadecimal);
        return decimalToBinary(decimal);
    }
}
