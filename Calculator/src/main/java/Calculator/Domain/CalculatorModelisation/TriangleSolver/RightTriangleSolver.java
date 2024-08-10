package Calculator.Domain.CalculatorModelisation.TriangleSolver;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RightTriangleSolver implements TriangleSolver {
    @Override
    public List<Double> solve(List<Double> features) {

        double a = features.get(0);
        double b = features.get(2);
        double c = features.get(4);

        double alphaDegrees = features.get(1);
        double betaDegrees = features.get(3);
        double alpha = Math.toRadians(alphaDegrees);
        double beta = Math.toRadians(betaDegrees);

        double h = features.get(5);
        double perimeter = 0;
        double area = 0;

        List<Double> resultat = new ArrayList<>();

        if(alphaDegrees > 0 && betaDegrees > 0){
            throw new IllegalArgumentException("Cannot solve based on angles. Need one side with an angle, two sides or " +
                    "the height and an angle to solve!");
        }
        if (a > 0 && b > 0) {
            c = Math.sqrt(a * a + b * b);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        } else if (a > 0 && c > 0) {
            checkHypothenuseConstraint(a, c, "a", "c");
            b = Math.sqrt(c * c - a * a);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        } else if (a > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            c = a / Math.sin(alpha);
            b = Math.sqrt(c * c - a * a);
            beta = 90 - alphaDegrees;
            alpha = alphaDegrees;
        } else if (a > 0 && betaDegrees > 0) {
            checkAngleValidity(betaDegrees, "β");
            c = a / Math.cos(beta);
            b = Math.sqrt(c * c - a * a);
            alpha = 90 - betaDegrees;
            beta = betaDegrees;
        } else if (b > 0 && c > 0) {
            checkHypothenuseConstraint(b, c, "b", "c");
            a = Math.sqrt(c * c - b * b);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        } else if (b > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            c = b / Math.cos(alpha);
            a = Math.sqrt(c * c - b * b);
            beta = 90 - alphaDegrees;
            alpha = alphaDegrees;
        } else if (b > 0 && betaDegrees > 0) {
            checkAngleValidity(betaDegrees, "β");
            c = b / Math.sin(beta);
            a = Math.sqrt(c * c - b * b);
            alpha = 90 - betaDegrees;
            beta = betaDegrees;
        } else if (c > 0 && alphaDegrees > 0) {
            checkAngleValidity(alphaDegrees, "𝛼");
            a = c * Math.sin(alpha);
            b = Math.sqrt(c * c - a * a);
            beta = 90 - alphaDegrees;
            alpha = alphaDegrees;
        } else if (c > 0 && betaDegrees > 0) {
            checkAngleValidity(betaDegrees, "β");
            a = c * Math.cos(beta);
            b = Math.sqrt(c * c - a * a);
            alpha = 90 - betaDegrees;
            beta = betaDegrees;
        }else if(a > 0 && h > 0){
            checkHeightConstraint(a, h, "h", "a");
            c = a * a / Math.sqrt(a * a - h * h);
            b = Math.sqrt(c * c - a * a);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        }else if(b > 0 && h > 0){
            checkHeightConstraint(b, h, "h", "b");
            c = b * b / Math.sqrt(b * b - h * h);
            a = Math.sqrt(c * c - b * b);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        } else if (c > 0 && h > 0) {
            checkHypothenuseConstraint(h, c, "h", "c");
            a = Math.sqrt(( c*c - Math.sqrt(Math.pow(c, 4) - 4*c*c*h*h))/2);
            b = Math.sqrt(c*c - a*a);
            alpha = Math.toDegrees(Math.asin(a / c));
            beta = 90 - alpha;
        } else if(h > 0 && alphaDegrees > 0){
            checkAngleValidity(alphaDegrees, "𝛼");
            a = h / Math.cos(alpha);
            b = h / Math.sin(alpha);
            c = Math.sqrt(a*a + b*b);
            beta = 90 - alphaDegrees;
            alpha = alphaDegrees;
        }else if(h > 0 && betaDegrees > 0){
            checkAngleValidity(betaDegrees, "β");
            a = h / Math.sin(beta);
            b = h / Math.cos(beta);
            c = Math.sqrt(a*a + b*b);
            alpha = 90 - betaDegrees;
            beta = betaDegrees;
        }

        perimeter = a + b + c;
        area = (a * b) / 2;
        if(h <= 0){
            h = (a * b) / c;
        }

        resultat.add(a);
        resultat.add(alpha);
        resultat.add(b);
        resultat.add(beta);
        resultat.add(c);
        resultat.add(h);
        resultat.add(perimeter);
        resultat.add(area);

        roundAll(resultat);
        return resultat;
    }

    private void checkAngleValidity(double angle, String angleText){
        if(angle >= 90){
            throw new IllegalArgumentException(angleText + " cannot be greater or equal to 90 (degrees)!");
        }
    }

    private void checkHypothenuseConstraint(double side, double hypothenus, String sideText, String hypothenuseText){
        if((side >= (hypothenus / 2) || side >= hypothenus) && sideText.equals("h")){
            throw new IllegalArgumentException(sideText + " cannot be greater or equal to half the size of " + hypothenuseText + "!");
        }
        if(side >= hypothenus){
            throw new IllegalArgumentException(sideText + " cannot be greater or equal to " + hypothenuseText + "!");
        }
    }

    private void checkHeightConstraint(double side, double height, String heightText, String sideText){
        if(height > side){
            throw new IllegalArgumentException(heightText + " cannot be greater or equal to " + sideText + "!");
        }
    }

    private void roundAll(List<Double> result) {
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }

}
