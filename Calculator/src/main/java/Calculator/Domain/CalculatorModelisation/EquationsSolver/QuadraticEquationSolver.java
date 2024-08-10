package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class QuadraticEquationSolver implements EquationSolver{
    @Override
    public List<Double> solve(List<Double>coefficients) {
        if (coefficients.size() != 3){
            throw new IllegalArgumentException("Wrong number of coefficients!");
        }

        List<Double> solutions = new ArrayList<Double>();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);

        if(a == 0){
            EquationSolver solver = new LinearEquationSolver();
            return solver.solve(List.of(b, c));
        }

        double discriminent = b * b - 4 * a * c;

        if(discriminent > 0){
            solutions.add((-b + Math.sqrt(discriminent)) / (2 * a));
            solutions.add((-b - Math.sqrt(discriminent)) / (2 * a));
        } else if (discriminent == 0) {
            solutions.add(-b/2*a);
        }else if (discriminent < 0){
            throw new IllegalArgumentException("This equation has no solution in R!");
        }
        roundAll(solutions);
        return solutions;
    }

    private void roundAll(List<Double> result){
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }
}
