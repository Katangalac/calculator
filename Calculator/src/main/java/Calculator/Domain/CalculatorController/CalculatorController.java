package Calculator.Domain.CalculatorController;

import Calculator.Domain.CalculatorModelisation.*;
import Calculator.Domain.CalculatorModelisation.Converters.*;

public class CalculatorController {
    private final Calculator calculator;

    public CalculatorController() {
        calculator = new Calculator(new NumberConverter(), new UnitConverter());
    }

    public CalculatorController(Calculator c){
        calculator = c;
    }

    public double calculate(String expression) {
        return calculator.calculate(expression);
    }

    public double convertUnit(UnitConversionType conversionType, double value) {
        return calculator.convertUnit(conversionType, value);
    }

    public int convertToDecimal(NumberConversionType conversionType, String expression) {
        return calculator.convertToDecimal(conversionType, expression);
    }

    public String convertDecimal(NumberConversionType conversionType, int decimalValue) {
        return calculator.convertDecimal(conversionType, decimalValue);
    }

    public String convertNonDecimalNumber(NumberConversionType conversionType, String expression) {
        return calculator.convertNonDecimalNumber(conversionType, expression);
    }
}
