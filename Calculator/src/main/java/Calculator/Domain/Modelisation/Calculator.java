package Calculator.Domain.Modelisation;
import java.util.HashMap;
import java.util.Map;
public class Calculator {
    private final Map<String, Operation> binaryOperations = new HashMap<>();
    private final Map<String, UnaryOperation> unaryOperations = new HashMap<>();
    private final INumberConverter numberConverter;
    private final IUnitConverter unitConverter;

    public Calculator(INumberConverter numberConverter, IUnitConverter unitConverter) {
        this.numberConverter = numberConverter;
        this.unitConverter = unitConverter;

        // binary operations Initialization
        binaryOperations.put("addition", new Addition());
        binaryOperations.put("substraction", new Substraction());
        binaryOperations.put("multiplication", new Multiplication());
        binaryOperations.put("division", new Division());
        binaryOperations.put("root", new NthRoot());
        binaryOperations.put("exponentiation", new Exponentiation());
        binaryOperations.put("exp10", new Exp10());
        binaryOperations.put("logarithm", new Logarithm());

        // unary operations Initialization
        unaryOperations.put("abs", new AbsoluteValue());
        unaryOperations.put("factorial", new Factorial());
        unaryOperations.put("negative", new Negative());
        unaryOperations.put("sin", new Sine());
        unaryOperations.put("cos", new Cosine());
        unaryOperations.put("tan", new Tangent());
        unaryOperations.put("cotan", new CoTangent());
        unaryOperations.put("cosec", new Cosecant());
        unaryOperations.put("sec", new Secant());

    }

    public double calculate(String operationName, double... operands) {
        if (unaryOperations.containsKey(operationName)) {
            if (operands.length != 1) {
                throw new IllegalArgumentException("Unary operation requires exactly one operand.");
            }
            return unaryOperations.get(operationName).calculate(operands[0]);
        } else if (binaryOperations.containsKey(operationName)) {
            if (operands.length != 2) {
                throw new IllegalArgumentException("Binary operation requires exactly two operands.");
            }
            return binaryOperations.get(operationName).calculate(operands[0], operands[1]);
        }
        throw new IllegalArgumentException("Unsupported operation: " + operationName);
    }

    public String convertNumber(String conversionType, String value) {
        return switch (conversionType) {
            case "decimalToBinary" -> numberConverter.decimalToBinary(Integer.parseInt(value));
            case "binaryToDecimal" -> Integer.toString(numberConverter.binaryToDecimal(value));
            case "decimalToHexadecimal" -> numberConverter.decimalToHexadecimal(Integer.parseInt(value));
            case "hexadecimalToDecimal" -> Integer.toString(numberConverter.hexadecimalToDecimal(value));
            case "binaryToHexadecimal" -> numberConverter.binaryToHexadecimal(value);
            case "hexadecimalToBinary" -> numberConverter.hexadecimalToBinary(value);
            default -> throw new IllegalArgumentException("Unsupported conversion type: " + conversionType);
        };
    }

    public double convertUnit(String conversionType, double value) {
        return switch (conversionType) {
            case "degreesToRadians" -> unitConverter.degreesToRadians(value);
            case "radiansToDegrees" -> unitConverter.radiansToDegrees(value);
            case "degreesToGradians" -> unitConverter.degreesToGradians(value);
            case "gradiansToDegrees" -> unitConverter.gradiansToDegrees(value);
            case "radiansToGradians" -> unitConverter.radiansToGradians(value);
            case "gradiansToRadians" -> unitConverter.gradiansToRadians(value);
            default -> throw new IllegalArgumentException("Unsupported conversion type: " + conversionType);
        };
    }

}

