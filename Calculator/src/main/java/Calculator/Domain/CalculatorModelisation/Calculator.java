package Calculator.Domain.CalculatorModelisation;

import Calculator.Domain.CalculatorModelisation.Converters.*;
import Calculator.Domain.CalculatorModelisation.EquationsSolver.*;
import Calculator.Domain.CalculatorModelisation.Operations.*;
import Calculator.Domain.CalculatorModelisation.EpressionManagement.*;
import Calculator.Domain.CalculatorModelisation.TriangleSolver.TriangleSolver;
import Calculator.Domain.CalculatorModelisation.TriangleSolver.TriangleSolverInitializer;
import Calculator.Domain.CalculatorModelisation.TriangleSolver.TriangleType;

import java.util.List;
import java.util.Map;

public class Calculator {
    private final Map<String, BinaryOperation> binaryOperations;
    private final Map<String, UnaryOperation> unaryOperations;
    private final Map<EquationType, EquationSolver> equationSolvers;
    private final Map<TriangleType, TriangleSolver> triangleSolvers;
    private final INumberConverter numberConverter;
    private final IUnitConverter unitConverter;
    private final IExpressionTree expressionTree;

    public Calculator(INumberConverter numberConverter, IUnitConverter unitConverter) {
        this.numberConverter = numberConverter;
        this.unitConverter = unitConverter;
        this.binaryOperations = OperationsInitializer.initializeBinaryOperations();
        this.unaryOperations = OperationsInitializer.initializeUnaryOperations();
        this.equationSolvers = EquationSolverInitializer.initilializeEquationSolvers();
        this.triangleSolvers = TriangleSolverInitializer.initilializeTriangleSolvers();
        this.expressionTree = new ExpressionTree(binaryOperations, unaryOperations);
    }

    public double calculate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }
        expressionTree.buildTree(expression);
        return expressionTree.evaluate();
    }

    public double convertUnit(UnitConversionType conversionType, double value) {
        if (conversionType == null) {
            throw new IllegalArgumentException("Conversion type cannot be null");
        }
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

    public int convertToDecimal(NumberConversionType conversionType, String expression) {
        if (conversionType == null) {
            throw new IllegalArgumentException("Conversion type cannot be null");
        }
        return switch (conversionType){
            case BINARY_TO_DECIMAL -> numberConverter.binaryToDecimal(expression);
            case HEXADECIMAL_TO_DECIMAL -> numberConverter.hexadecimalToDecimal(expression);
            default -> throw new IllegalArgumentException("Unsupported conversion to decimal: " + conversionType);
        };
    }

    public String convertDecimal(NumberConversionType conversionType, int decimalValue) {
        if (conversionType == null) {
            throw new IllegalArgumentException("Conversion type cannot be null");
        }
        return switch (conversionType){
            case DECIMAL_TO_BINARY -> numberConverter.decimalToBinary(decimalValue);
            case DECIMAL_TO_HEXADECIMAL -> numberConverter.decimalToHexadecimal(decimalValue);
            default -> throw new IllegalArgumentException("Unsupported conversion for decimal: " + conversionType);
        };
    }

    public String convertNonDecimalNumber(NumberConversionType conversionType, String expression) {
        if (conversionType == null) {
            throw new IllegalArgumentException("Conversion type cannot be null");
        }
        return switch (conversionType){
            case BINARY_TO_HEXADECIMAL -> numberConverter.binaryToHexadecimal(expression);
            case HEXADECIMAL_TO_BINARY -> numberConverter.hexadecimalToBinary(expression);
            default -> throw new IllegalArgumentException("Unsupported conversion for non decimal number: " + conversionType);
        };
    }

    public List<Double> solveEquation(EquationType type, List<Double> coefficients){
        if (type == null) {
            throw new IllegalArgumentException("Equation type cannot be null");
        }

        EquationSolver solver = equationSolvers.get(type);

        if (solver == null) {
            throw new IllegalArgumentException("No solver available for the given equation type: " + type.getSymbol());
        }

        return solver.solve(coefficients);
    }

    public List<Double> solveTriangle(TriangleType type, List<Double> features){
        if (type == null) {
            throw new IllegalArgumentException("Triangle type cannot be null");
        }

        TriangleSolver solver = triangleSolvers.get(type);

        if (solver == null) {
            throw new IllegalArgumentException("No solver available for the given triangle type: " + type.getSymbol());
        }
        return solver.solve(features);
    }
}

