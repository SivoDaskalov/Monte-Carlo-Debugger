/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import org.junit.Before;
import org.junit.Test;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationRequestJaxbTestCase extends JaxbTestCase {

    private final int TREE_SIZE = 3;
    private SimulationRequest request;

    public SimulationRequestJaxbTestCase() {
    }

    @Before
    public void setUp() {
        setupJaxbContext(SimulationRequest.class);

        SimulationProperties properties = new SimulationProperties();
        properties.setTitle("Test simulation");
        properties.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        properties.setSimulationRuns(10000);

        request = new SimulationRequest();
        request.setProperties(properties);
        request.setVariableRegistry(TestHelper.makeVariableRegistry(TREE_SIZE));
        request.setFormula(TestHelper.buildNodeTree(TREE_SIZE));
    }

    @Test
    public void testRequestMarshalling() {
        SimulationRequest unmarshaled = doMarhshalUnmarshal(request, "target/SimulationRequest.xml");
    }

}