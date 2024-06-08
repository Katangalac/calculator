package Calculator.Domain.CalculatorModelisation;
import Calculator.Domain.CalculatorModelisation.Converters.*;
import Calculator.Domain.CalculatorModelisation.Operations.*;
import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.LogarithmicOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations.*;
import Calculator.Domain.CalculatorModelisation.EpressionManagement.*;


import java.util.HashMap;
import java.util.Map;
public class Calculator {
    private final Map<String, BinaryOperation> binaryOperations = new HashMap<>();
    private final Map<String, UnaryOperation> unaryOperations = new HashMap<>();
    private final Map<String, MultiTypeOperation> multiTypeOperations = new HashMap<>();
    private final INumberConverter numberConverter;
    private final IUnitConverter unitConverter;
    private final IExpressionParser expressionParser;
    private final ExpressionEvaluator expressionEvaluator;

    public Calculator(INumberConverter numberConverter, IUnitConverter unitConverter, IExpressionParser parser, ExpressionEvaluator evaluator) {
        this.numberConverter = numberConverter;
        this.unitConverter = unitConverter;
        this.expressionParser = parser;
        this.expressionEvaluator = evaluator;
        initializeOperations();
    }

    private void initializeOperations(){
        // binary operations Initialization
        binaryOperations.put("+", new Addition());
        binaryOperations.put("-", new Substraction());
        binaryOperations.put("*", new Multiplication());
        binaryOperations.put("/", new Division());
        binaryOperations.put("root", new Root());
        binaryOperations.put("exp", new Exponentiation());
        binaryOperations.put("exp10", new Exp10());
        binaryOperations.put("log", new Logarithm());

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
        unaryOperations.put("ln", new Ln());

        // multi type operations and binary operations Initialization
        //multiTypeOperations.put("log", new Logarithm());
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
        }else if(multiTypeOperations.containsKey(operationName)){
            if(operands.length == 1){
                return multiTypeOperations.get(operationName).calculate(operands[0]);
            }else if (operands.length == 2){
                return multiTypeOperations.get(operationName).calculate(operands[0], operands[1]);
            }else{
                throw new IllegalArgumentException("Too many operands, one or two operands required.");
            }
        }
        throw new IllegalArgumentException("Unsupported operation: " + operationName);
    }

    public String convertNumber(NumberConversionType conversionType, String value) {
        return switch (conversionType) {
            case DECIMAL_TO_BINARY -> numberConverter.decimalToBinary(Integer.parseInt(value));
            case BINARY_TO_DECIMAL -> Integer.toString(numberConverter.binaryToDecimal(value));
            case DECIMAL_TO_HEXADECIMAL -> numberConverter.decimalToHexadecimal(Integer.parseInt(value));
            case HEXADECIMAL_TO_DECIMAL -> Integer.toString(numberConverter.hexadecimalToDecimal(value));
            case BINARY_TO_HEXADECIMAL -> numberConverter.binaryToHexadecimal(value);
            case HEXADECIMAL_TO_BINARY -> numberConverter.hexadecimalToBinary(value);
            default -> throw new IllegalArgumentException("Unsupported conversion type: " + conversionType);
        };
    }

    public double convertUnit(UnitConversionType conversionType, double value) {
        return switch (conversionType) {
            case DEGREES_TO_RADIANS -> unitConverter.degreesToRadians(value);
            case RADIANS_TO_DEGREES -> unitConverter.radiansToDegrees(value);
            case DEGREES_TO_GRADIANS -> unitConverter.degreesToGradians(value);
            case GRADIANS_TO_DEGREES -> unitConverter.gradiansToDegrees(value);
            case RADIANS_TO_GRADIANS -> unitConverter.radiansToGradians(value);
            case GRADIANS_TO_RADIANS -> unitConverter.gradiansToRadians(value);
            default -> throw new IllegalArgumentException("Unsupported conversion type: " + conversionType);
        };
    }

}

