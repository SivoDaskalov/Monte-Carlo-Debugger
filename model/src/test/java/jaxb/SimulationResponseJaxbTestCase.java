/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import java.util.Collections;
import node.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        response.setValues(Collections.EMPTY_LIST);
    }

    @Test
    public void testResponseMarshalling() {
        SimulationResponse unmarshaled = doMarhshalUnmarshal(response, "target/SimulationResponse.xml");
        Assert.assertNotNull(unmarshaled);
        Assert.assertNotNull(unmarshaled.getProperties());
        Assert.assertNotNull(unmarshaled.getVariableRegistry().getVariables());
        Assert.assertNotNull(unmarshaled.getFormula());
        Assert.assertNotNull(unmarshaled.getValues());
    }
}
