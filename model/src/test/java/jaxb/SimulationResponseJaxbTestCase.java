/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package jaxb;

import java.util.Arrays;
import node.Node;
import org.junit.Before;
import org.junit.Test;
import simulation.NodeValues;
import simulation.SimulationProperties;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationResponseJaxbTestCase extends JaxbTestCase {

    private final int TREE_SIZE = 5;
    private final int RUNS = 20000;
    private SimulationResponse response;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponse.class);

        SimulationProperties properties = new SimulationProperties();
        properties.setTitle("Test simulation");
        properties.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        properties.setSimulationRuns(RUNS);

        Node formula = TestHelper.buildNodeTree(TREE_SIZE);
        StochasticVariableRegistry variables = TestHelper.makeVariableRegistry(TREE_SIZE);

        response = new SimulationResponse();
        response.setProperties(properties);
        response.setVariableRegistry(variables);
        response.setFormula(formula);
        // Not related to the actual formula or properties!
        response.setValues(Arrays.asList(
                new NodeValues("1", new double[]{1.1, 2.2, 3.3}),
                new NodeValues("2", new double[]{4.4, 5.5, 6.6}),
                new NodeValues("3", new double[]{7.7, 8.8, 9.9})
        ));
    }

    @Test
    public void testResponseMarshalling() {
        SimulationResponse unmarshaled = doMarhshalUnmarshal(response, "target/SimulationResponse.xml");
        assertEquals(response, unmarshaled);
    }
}
