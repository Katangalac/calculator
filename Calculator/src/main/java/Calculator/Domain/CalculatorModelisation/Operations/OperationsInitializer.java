package Calculator.Domain.CalculatorModelisation.Operations;

import Calculator.Domain.CalculatorModelisation.Operations.BasicArithemticOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.AdditionnalOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.ExponentialOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.LogarithmicOperations.*;
import Calculator.Domain.CalculatorModelisation.Operations.TrigonometricOperations.*;

import java.util.HashMap;
import java.util.Map;

public class OperationsInitializer {
    public static Map<String, BinaryOperation> initializeBinaryOperations() {
        Map<String, BinaryOperation> binaryOperations = new HashMap<>();
        binaryOperations.put("+", new Addition());
        binaryOperations.put("-", new Substraction());
        binaryOperations.put("x", new Multiplication());
        binaryOperations.put("/", new Division());
        binaryOperations.put("root", new Root());
        binaryOperations.put("exp", new Exponentiation());
        binaryOperations.put("exp10", new Exp10());
        binaryOperations.put("log", new Logarithm());
        binaryOperations.put("%", new Modulo());
        return binaryOperations;
    }

    public static Map<String, UnaryOperation> initializeUnaryOperations() {
        Map<String, UnaryOperation> unaryOperations = new HashMap<>();
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
        return unaryOperations;
    }
}
