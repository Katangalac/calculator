package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.util.List;

public interface EquationSolver {
    List<Double> solve(List<Double> coefficients);
}
