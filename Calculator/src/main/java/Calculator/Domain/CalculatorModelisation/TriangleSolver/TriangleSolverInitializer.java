package Calculator.Domain.CalculatorModelisation.TriangleSolver;

import java.util.HashMap;
import java.util.Map;

public class TriangleSolverInitializer {
    public static Map<TriangleType, TriangleSolver> initilializeTriangleSolvers(){
        Map<TriangleType, TriangleSolver> triangleSolvers = new HashMap<>();
        triangleSolvers.put(TriangleType.RIGHT, new RightTriangleSolver());
        triangleSolvers.put(TriangleType.ARBITRARY, new ArbitraryTriangleSolver());;
        return triangleSolvers;
    }
}
