package Calculator.Domain.CalculatorModelisation.TriangleSolver;

public enum TriangleType {
    RIGHT("Right triangle"),
    ARBITRARY("Arbitrary triangle");

    private final String symbol;

    TriangleType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TriangleType fromString(String s) {
        for (TriangleType type : TriangleType.values()) {
            if (type.symbol.equals(s)) {
                return type;
            }
        }
        return null;
    }
}
