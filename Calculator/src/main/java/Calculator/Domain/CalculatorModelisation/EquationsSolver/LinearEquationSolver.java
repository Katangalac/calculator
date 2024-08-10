package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class LinearEquationSolver implements EquationSolver{
    @Override
    public List<Double> solve(List<Double> coefficients) {
        if (coefficients.size() != 2) {
            throw new IllegalArgumentException("Wrong number of coefficients!");
        }

        List<Double> solutions = new ArrayList<Double>();
        double a = coefficients.get(0);
        double b = coefficients.get(1);

        if(a == 0){
            throw new IllegalArgumentException("No solution found!");
        }

        if(b == 0){
            solutions.add(0.0);
            return solutions;
        }

        solutions.add(-b/a);
        roundAll(solutions);
        return solutions;
    }

    private void roundAll(List<Double> result){
        result.replaceAll(val -> new BigDecimal(val).setScale(4, RoundingMode.HALF_UP).doubleValue());
    }
}
