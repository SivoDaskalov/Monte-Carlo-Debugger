/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import node.Node;
import org.junit.Before;
import org.junit.Test;
import simulation.NodeValuesWrapper;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.response.SimulationResponseImpl;
import util.TestHelper;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class SimulationResponseJaxbTestCase extends JaxbTestCase {

    private final int TREE_SIZE = 3;
    private SimulationResponseImpl response;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponseImpl.class);

        SimulationConfigurationImpl configuration = new SimulationConfigurationImpl();
        configuration.setTitle("Test simulation");
        configuration.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        configuration.setSimulationRuns(10);

        Node formula = TestHelper.buildNodeTree(TREE_SIZE);
        StochasticVariableRegistryImpl variables = TestHelper.makeVariableRegistry(TREE_SIZE);

        response = new SimulationResponseImpl();
        response.setConfiguration(configuration);
        response.setVariables(variables);
        response.setFormula(formula);
        response.setNodeValues(new NodeValuesWrapper());
    }

    @Test
    public void testResponseMarshalling() {
        SimulationResponseImpl unmarshaled = doMarhshalUnmarshal(response, "target/SimulationResponse.xml");
    }
}
