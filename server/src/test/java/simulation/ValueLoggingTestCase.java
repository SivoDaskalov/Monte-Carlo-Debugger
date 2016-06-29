/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation;

import node.Node;
import node.impl.ConstantNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.interfaces.SimulationManager;
import simulation.manager.ParallelSimulationManager;

/**
 *
 * @author sdaskalov
 */
public class ValueLoggingTestCase {

    private final int RUNS = 100;
    private final int THREADS = 100;
    private final double constValue = 100.0;
    private SimulationProperties configuration;
    private Node root;
    private StochasticVariableRegistry variables;

    public ValueLoggingTestCase() {
    }

    @Before
    public void setUp() {
        configuration = new SimulationProperties();
        configuration.setTitle("Constant tree configuration");
        configuration.setDescription(
                "This configuration test whether the value logger manages to log all values in a concurrent environment");
        configuration.setSimulationRuns(RUNS);
        variables = new StochasticVariableRegistry();
        root = new ConstantNode(constValue);
    }

    @Test
    public void testValueLogging() {
        // Assert that all values have managed to be logged
        double[] constValues = doSimulate(configuration, root, variables).getValues().get(0).getValues();
        Assert.assertEquals(100, constValues.length);
        for (double value : constValues) {
            Assert.assertEquals(constValue, value, 0.0);
        }
    }

    private SimulationResponse doSimulate(SimulationProperties configuration, Node root, StochasticVariableRegistry variables) {
        SimulationManager simulationManager = new ParallelSimulationManager(root, variables, configuration.getSimulationRuns(), RUNS / THREADS);
        simulationManager.run();
        simulationManager.await();
        return new SimulationResponse(configuration, variables, root, simulationManager);
    }
}
