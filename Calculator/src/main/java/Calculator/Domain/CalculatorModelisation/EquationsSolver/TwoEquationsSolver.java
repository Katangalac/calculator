package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TwoEquationsSolver implements EquationSolver{
    @Override
    public List<Double> solve(List<Double> coefficients) {
        if(coefficients.size() != 6){
            throw new IllegalArgumentException("Wrong number of coefficients");
        }

        List<Double> solutions = new ArrayList<Double>();
        double a1 = coefficients.get(0);
        double b1 = coefficients.get(1);
        double c1 = coefficients.get(2);

        double a2 = coefficients.get(3);
        double b2 = coefficients.get(4);
        double c2 = coefficients.get(5);

        double d = a1 * b2 - a2 * b1;

        if(d == 0){
            throw new IllegalArgumentException("No solution found");
        }

        double dx = c1 * b2 - c2 * b1;
        double dy = a1 * c2 - a2 * c1;

        solutions.add(dx/d);
        solutions.add(dy/d);
        roundAll(solutions);
        return solutions;
    }

    private void roundAll(List<Double> result){
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }
}
