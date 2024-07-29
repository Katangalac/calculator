package Calculator.Domain.CalculatorModelisation.EquationsSolver;

import java.util.HashMap;
import java.util.Map;

public class EquationSolverInitializer {
    public static Map<EquationType, EquationSolver> initilializeEquationSolvers(){
        Map<EquationType, EquationSolver> equationSolvers = new HashMap<>();
        equationSolvers.put(EquationType.LINEAR, new LinearEquationSolver());
        equationSolvers.put(EquationType.QUADRATIC, new QuadraticEquationSolver());
        equationSolvers.put(EquationType.TWO_EQUATIONS, new TwoEquationsSolver());
        equationSolvers.put(EquationType.THREE_EQUATIONS, new ThreeEquationsSolver());
        return equationSolvers;
    }
}
