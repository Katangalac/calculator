package Calculator.Domain.CalculatorModelisation.Operations;

//symbol : √

public enum Operations {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    FACT("+!"),
    ABS("abs"),
    LOG("log"),
    EXP10("exp10"),
    EXP("exp"),
    ROOT("root"),
    SIN("sin"),
    COS("cos"),
    SEC("sec"),
    COSEC("cosec"),
    TAN("tan"),
    COTAN("cotan"),
    MODULO("%"),
    NULL("null");

    private final String symbol;

    Operations(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operations fromString(String s) {
        for (Operations op : Operations.values()) {
            if (op.symbol.equals(s)) {
                return op;
            }
        }
        return NULL;
    }
}