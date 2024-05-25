package Calculator.Domain.CalculatorModelisation.Converters;

public interface INumberConverter {
    String decimalToBinary(int decimal);
    int binaryToDecimal(String binary);
    String decimalToHexadecimal(int decimal);
    int hexadecimalToDecimal(String hexadecimal);
    String binaryToHexadecimal(String binary);
    String hexadecimalToBinary(String hexadecimal);
}
