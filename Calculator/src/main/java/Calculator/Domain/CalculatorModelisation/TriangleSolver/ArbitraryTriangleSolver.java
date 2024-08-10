package Calculator.Domain.CalculatorModelisation.TriangleSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ArbitraryTriangleSolver implements TriangleSolver {
    @Override
    public List<Double> solve(List<Double> features) {
        double a = features.get(0);
        double b = features.get(2);
        double c = features.get(4);

        double alphaDegrees = features.get(1);
        double betaDegrees = features.get(3);
        double gammaDegrees = features.get(5);

        double alpha = Math.toRadians(alphaDegrees);
        double beta = Math.toRadians(betaDegrees);
        double gamma = Math.toRadians(gammaDegrees);

        double h = 0;
        double perimeter = 0;
        double area = 0;

        List<Double> results = new ArrayList<>();

        if(a > 0 && b > 0 && c > 0) {
            checkSidesConstraints(a,b,c);
            double cosAlpha = (b * b + c * c - a * a) / (2 * b * c);
            if (cosAlpha >= -1 && cosAlpha <= 1) {
                alpha = Math.toDegrees(Math.acos(cosAlpha));
                beta = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
                gamma = 180.0 - alpha - beta;
            }else{
                throw new IllegalArgumentException("cos(𝛼) must be between -1 and 1 but was " + cosAlpha + "!");
            }
        }else if(a > 0 && b > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            beta = Math.toDegrees(Math.asin(b*Math.sin(alpha)/a));
            gamma = 180 - alphaDegrees - beta;
            c = a*Math.sin(Math.toRadians(gamma))/Math.sin(alpha);
            alpha = alphaDegrees;
        }else if(a > 0 && b > 0 && betaDegrees > 0) {
            checkAngleValidity(betaDegrees, "β");
            alpha = Math.toDegrees(Math.asin(a*Math.sin(beta)/b));
            gamma = 180 - alpha - betaDegrees;
            c = b*Math.sin(Math.toRadians(gamma))/Math.sin(beta);
            beta = betaDegrees;
        }else if(a > 0 && b > 0 && gammaDegrees > 0) {
            checkAngleValidity(gammaDegrees, "૪");
            c = Math.sqrt(a*a + b*b - 2*a*b*Math.cos(gamma));
            alpha = Math.toDegrees(Math.acos((c*c + b*b - a*a)/(2*b*c)));
            beta = 180 - alpha - gammaDegrees;
            gamma = gammaDegrees;
        }else if(a > 0 && c > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            gamma = Math.toDegrees(Math.asin(c*Math.sin(alpha)/a));
            beta = 180 - alphaDegrees - gamma;
            b = a*Math.sin(Math.toRadians(beta))/Math.sin(alpha);
            alpha = alphaDegrees;
        }else if(a > 0 && c > 0 && betaDegrees > 0) {
            checkAngleValidity(betaDegrees, "β");
            b = Math.sqrt(a*a + c*c -2*a*c*Math.cos(beta));
            alpha = Math.toDegrees(Math.acos((b*b + c*c - a*a)/(2*b*c)));
            gamma = 180 - alpha - betaDegrees;
            beta = betaDegrees;
        }else if(a > 0 && c > 0 && gammaDegrees > 0){
            checkAngleValidity(gammaDegrees, "૪");
            alpha = Math.toDegrees(Math.asin(a*Math.sin(gamma)/c));
            beta = 180 - alpha - gammaDegrees;
            b = c*Math.sin(Math.toRadians(beta))/Math.sin(gamma);
            gamma = gammaDegrees;
        }else if(b > 0 && c > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            a = Math.sqrt(b*b + c*c -2*b*c*Math.cos(alpha));
            beta = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
            gamma = 180 - alphaDegrees - beta;
            alpha = alphaDegrees;
        }else if(b > 0 && c > 0 && beta > 0) {
            checkAngleValidity(betaDegrees, "β");
            gamma = Math.toDegrees(Math.asin(c*Math.sin(beta)/b));
            alpha = 180 - gamma - betaDegrees;
            a = b*Math.sin(Math.toRadians(alpha))/Math.sin(beta);
            beta = betaDegrees;
        }else if(b > 0 && c > 0 && gammaDegrees > 0){
            checkAngleValidity(gammaDegrees, "૪");
            beta = Math.toDegrees(Math.asin(b*Math.sin(gamma)/c));
            alpha = 180 - beta - gammaDegrees;
            a = c*Math.sin(Math.toRadians(alpha))/Math.sin(gamma);
            gamma = gammaDegrees;
        }else if(a > 0 && alphaDegrees > 0 && betaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(betaDegrees, "β");
            checkAngleConstraint(alphaDegrees, betaDegrees);
            gamma = 180 - alphaDegrees - betaDegrees;
            b = a*Math.sin(beta)/Math.sin(alpha);
            c = a*Math.sin(Math.toRadians(gamma))/Math.sin(alpha);
            beta = betaDegrees;
            alpha = alphaDegrees;
        }else if(a > 0 && alphaDegrees > 0 && gammaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(alphaDegrees, gammaDegrees);
            beta = 180 - gammaDegrees - alphaDegrees;
            b = a*Math.sin(Math.toRadians(beta))/Math.sin(alpha);
            c = a*Math.sin(gamma)/Math.sin(alpha);
            alpha = alphaDegrees;
            gamma= gammaDegrees;
        }else if(a > 0 && betaDegrees > 0 && gammaDegrees > 0){
            checkAngleValidity(betaDegrees, "β");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(gammaDegrees, betaDegrees);
            alpha = 180 - gammaDegrees - betaDegrees;
            b = a*Math.sin(beta)/Math.sin(Math.toRadians(alpha));
            c = a*Math.sin(gamma)/Math.sin(Math.toRadians(alpha));
            beta = betaDegrees;
            gamma = gammaDegrees;
        }else if(b > 0 && alphaDegrees > 0 && betaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(betaDegrees, "β");
            checkAngleConstraint(alphaDegrees, betaDegrees);
            gamma = 180 - alphaDegrees - betaDegrees;
            a = b*Math.sin(alpha)/Math.sin(beta);
            c = b*Math.sin(Math.toRadians(gamma))/Math.sin(beta);
            beta = betaDegrees;
            alpha = alphaDegrees;
        }else if(b > 0 && alphaDegrees > 0 && gammaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(alphaDegrees, gammaDegrees);
            beta = 180 - gammaDegrees - alphaDegrees;
            a = b*Math.sin(alpha)/Math.sin(Math.toRadians(beta));
            c = b*Math.sin(gamma)/Math.sin(Math.toRadians(beta));
            alpha = alphaDegrees;
            gamma= gammaDegrees;
        }else if(b > 0 && betaDegrees > 0 && gammaDegrees > 0){
            checkAngleValidity(betaDegrees, "β");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(gammaDegrees, betaDegrees);
            alpha = 180 - gammaDegrees - betaDegrees;
            a = b*Math.sin(Math.toRadians(alpha))/Math.sin(beta);
            c = b*Math.sin(gamma)/Math.sin(beta);
            beta = betaDegrees;
            gamma = gammaDegrees;
        }else if(c > 0 && alphaDegrees > 0 && betaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(betaDegrees, "β");
            checkAngleConstraint(alphaDegrees, betaDegrees);
            gamma = 180 - alphaDegrees - betaDegrees;
            a = c*Math.sin(alpha)/Math.sin(Math.toRadians(gamma));
            b = c*Math.sin(beta)/Math.sin(Math.toRadians(gamma));
            beta = betaDegrees;
            alpha = alphaDegrees;
        }else if(c > 0 && alphaDegrees > 0 && gammaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(alphaDegrees, gammaDegrees);
            beta = 180 - gammaDegrees - alphaDegrees;
            a = c*Math.sin(alpha)/Math.sin(gamma);
            b = c*Math.sin(Math.toRadians(beta))/Math.sin(gamma);
            alpha = alphaDegrees;
            gamma= gammaDegrees;
        }else if(c > 0 && betaDegrees > 0 && gammaDegrees > 0){
            checkAngleValidity(betaDegrees, "β");
            checkAngleValidity(gammaDegrees, "૪");
            checkAngleConstraint(gammaDegrees, betaDegrees);
            alpha = 180 - gammaDegrees - betaDegrees;
            a = c*Math.sin(Math.toRadians(alpha))/Math.sin(gamma);
            b = c*Math.sin(beta)/Math.sin(gamma);
            beta = betaDegrees;
            gamma = gammaDegrees;
        }

        perimeter = a + b + c;
        area = a*b*Math.sin(Math.toRadians(gamma))/2;
        h = 2*area/c;

        results.add(a);
        results.add(alpha);
        results.add(b);
        results.add(beta);
        results.add(c);
        results.add(gamma);
        results.add(h);
        results.add(perimeter);
        results.add(area);

        roundAll(results);
        return results;
    }

    private void checkAngleValidity(double angle, String angleText){
        if(angle >= 180){
            throw new IllegalArgumentException(angleText + " cannot be greater or equal to 90 (degrees)!");
        }
    }

    private void checkAngleConstraint(double angle1, double angle2){
        if(angle1 + angle2 >= 180){
            throw new IllegalArgumentException("The sum of 2 angles cannot be greater or equal to 180 (degrees)!");
        }
    }

    private void checkSidesConstraints(double a, double b, double c){
        if(a + b >= c){
            throw new IllegalArgumentException("The sum of 2 sides must be larger than the third (base)!");
        }
    }

    private void roundAll(List<Double> result) {
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }

}
