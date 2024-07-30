package Calculator.Domain.CalculatorModelisation.Converters;

public enum NumberConversionType {
    DECIMAL_TO_BINARY("DecimalBinary"),
    BINARY_TO_DECIMAL("BinaryDecimal"),
    DECIMAL_TO_HEXADECIMAL("DecimalHexadecimal"),
    HEXADECIMAL_TO_DECIMAL("HexadecimalDecimal"),
    BINARY_TO_HEXADECIMAL("BinaryHexadecimal"),
    HEXADECIMAL_TO_BINARY("HexadecimalBinary");

    private final String stringValue;

    NumberConversionType(String value) {
        this.stringValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static NumberConversionType fromString(String s) {
        for (NumberConversionType type : NumberConversionType.values()) {
            if (type.getStringValue().equals(s)) {
                return type;
            }
        }
        return null;
    }
}
