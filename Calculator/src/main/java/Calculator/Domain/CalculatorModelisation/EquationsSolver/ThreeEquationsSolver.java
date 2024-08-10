package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ThreeEquationsSolver implements EquationSolver{
    @Override
    public List<Double> solve(List<Double> coefficients) {
        if (coefficients.size() != 12) {
            throw new IllegalArgumentException("Wrong number of coefficients!");
        }

        List<Double> solutions = new ArrayList<Double>();
        double a1 = coefficients.get(0);
        double b1 = coefficients.get(1);
        double c1 = coefficients.get(2);
        double d1 = coefficients.get(3);

        double a2 = coefficients.get(4);
        double b2 = coefficients.get(5);
        double c2 = coefficients.get(6);
        double d2 = coefficients.get(7);

        double a3 = coefficients.get(8);
        double b3 = coefficients.get(9);
        double c3 = coefficients.get(10);
        double d3 = coefficients.get(11);;

        double d = (a1*b2*c3 + b1*c2*a3 + c1*a2*b3) - (c1*b2*a3 + b1*a2*c3 + a1*c2*b3);
        if(d == 0){
            throw new IllegalArgumentException("No solution found!");
        }

        double dx = (d1*b2*c3 + b1*c2*d3 + c1*d2*b3) - (c1*b2*d3 + b1*d2*c3 + d1*c2*b3);
        double dy = (a1*d2*c3 + d1*c2*a3 + c1*a2*d3) - (c1*d2*a3 + d1*a2*c3 + a1*c2*d3);
        double dz = (a1*b2*d3 + b1*d2*a3 + d1*a2*b3) - (d1*b2*a3 + b1*a2*d3 + a1*d2*b3);

        solutions.add(dx/d);
        solutions.add(dy/d);
        solutions.add(dz/d);
        roundAll(solutions);
        return solutions;
    }

    private void roundAll(List<Double> result){
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }
}
