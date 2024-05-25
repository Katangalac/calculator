package Calculator.Domain.CalculatorModelisation.Converters;

public interface IUnitConverter {
    double degreesToRadians(double degrees);
    double radiansToDegrees(double radians);
    double degreesToGradians(double degrees);
    double gradiansToDegrees(double gradians);
    double radiansToGradians(double radians);
    double gradiansToRadians(double gradians);
}
