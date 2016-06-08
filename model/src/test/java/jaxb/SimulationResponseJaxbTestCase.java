/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import node.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.NodeValuesWrapper;
import simulation.SimulationProperties;
import simulation.SimulationResponse;
import util.TestHelper;
import simulation.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class SimulationResponseJaxbTestCase extends JaxbTestCase {

    private final int TREE_SIZE = 3;
    private SimulationResponse response;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponse.class);

        SimulationProperties properties = new SimulationProperties();
        properties.setTitle("Test simulation");
        properties.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        properties.setSimulationRuns(10);

        Node formula = TestHelper.buildNodeTree(TREE_SIZE);
        StochasticVariableRegistry variables = TestHelper.makeVariableRegistry(TREE_SIZE);

        response = new SimulationResponse();
        response.setProperties(properties);
        response.setVariableRegistry(variables);
        response.setFormula(formula);
        response.setNodeValues(new NodeValuesWrapper());
    }

    @Test
    public void testResponseMarshalling() {
        SimulationResponse unmarshaled = doMarhshalUnmarshal(response, "target/SimulationResponse.xml");
        Assert.assertNotNull(unmarshaled);
        Assert.assertNotNull(unmarshaled.getProperties());
        Assert.assertNotNull(unmarshaled.getVariableRegistry().getVariables());
        Assert.assertNotNull(unmarshaled.getFormula());
        Assert.assertNotNull(unmarshaled.getNodeValues());
    }
}
