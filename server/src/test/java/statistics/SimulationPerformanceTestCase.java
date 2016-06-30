/*
 *  Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package statistics;

import node.Node;
import node.impl.VariableNode;
import node.impl.group.SumNode;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationProperties;
import simulation.StochasticVariableRegistry;
import simulation.interfaces.SimulationManager;
import simulation.manager.ParallelSimulationManager;
import variable.impl.UniformVariable;

/**
 *
 * @author sdaskalov
 */
public class SimulationPerformanceTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SimulationPerformanceTestCase.class);
    private static final int threadLoad = 100000 * 1000 / 4;

    private SimulationProperties makeProperties(int simulationRuns) {
        return new SimulationProperties("Title", "Description", simulationRuns);
    }

    private StochasticVariableRegistry makeVariables() {
        StochasticVariableRegistry variables = new StochasticVariableRegistry();
        variables.addVariable(new UniformVariable("X"));
        return variables;
    }

    private Node makeFormula(int size) {
        if (size == 0) {
            return null;
        }
        SumNode sum = new SumNode();
        for (int i = 1; i < size; i++) {
            sum.addChild(new VariableNode("X"));
        }
        return sum;
    }

    @Ignore
    @Test
    public void logSimulationDuration() {
        int runs = 10;
        int step = 1000;
        int roof = 10000;

        System.out.print("Size\tRuns\t");
        for (int n = 0; n < runs; n++) {
            System.out.print("T" + (n + 1));
            if (n == runs - 1) {
                System.out.print("\n");
            } else {
                System.out.print("\t");
            }
        }

        for (int i = step; i <= roof; i += step) {
            for (int j = step; j <= roof; j += step) {
                System.out.print(i + "\t" + j + "\t");
                for (int n = 0; n < runs; n++) {
                    simulate(i, j);
                    if (n == runs - 1) {
                        System.out.print("\n");
                    } else {
                        System.out.print("\t");
                    }
                }
            }
        }
    }

    public void simulate(int size, int runs) {
        long start = System.currentTimeMillis();
        Node root = makeFormula(size);
        StochasticVariableRegistry variables = makeVariables();
        SimulationManager simulationManager = new ParallelSimulationManager(root, variables, runs, threadLoad);
        simulationManager.run();
        simulationManager.await();
        System.out.print((System.currentTimeMillis() - start));
    }
}
