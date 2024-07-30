package Calculator.Domain.CalculatorModelisation.Converters;

import Calculator.Domain.CalculatorModelisation.TriangleSolver.TriangleType;

public enum UnitConversionType {
    DEGREES_TO_RADIANS("DegreesRadians"),
    RADIANS_TO_DEGREES("RadiansDegrees"),
    DEGREES_TO_GRADIANS("DegreesGradians"),
    GRADIANS_TO_DEGREES("GradiansDegrees"),
    RADIANS_TO_GRADIANS("RadiansGradians"),
    GRADIANS_TO_RADIANS("GradiansRadians");

    private final String stringValue;

    UnitConversionType(String value) {
        this.stringValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static UnitConversionType fromString(String s) {
        for (UnitConversionType type : UnitConversionType.values()) {
            if (type.getStringValue().equals(s)) {
                return type;
            }
        }
        return null;
    }
}
