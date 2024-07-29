package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import Calculator.Domain.CalculatorModelisation.Operations.Operations;

public enum EquationType {
    LINEAR("Linear Equation"),
    QUADRATIC("Quadratic Equation"),
    TWO_EQUATIONS("system of 2 equations with 2 unknowns"),
    THREE_EQUATIONS("system of 3 equations with 3 unknowns");

    private final String symbol;

    EquationType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static EquationType fromString(String s) {
        for (EquationType type : EquationType.values()) {
            if (type.symbol.equals(s)) {
                return type;
            }
        }
        return null;
    }
}
