package Calculator.Domain.Modelisation;

public class UnitConverter {
    public double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public double degreesToGradians(double degrees) {
        return degrees * (10.0 / 9.0);
    }

    public double gradiansToDegrees(double gradians) {
        return gradians * (9.0 / 10.0);
    }

    public double radiansToGradians(double radians) {
        double degrees = radiansToDegrees(radians);
        return degreesToGradians(degrees);
    }

    public double gradiansToRadians(double gradians) {
        double degrees = gradiansToDegrees(gradians);
        return degreesToRadians(degrees);
    }
}
