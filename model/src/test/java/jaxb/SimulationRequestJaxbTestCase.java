/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import org.junit.Before;
import org.junit.Test;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.request.SimulationRequestImpl;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationRequestJaxbTestCase extends JaxbTestCase {
    
    private final int TREE_SIZE = 3;
    private SimulationRequestImpl request;
    
    public SimulationRequestJaxbTestCase() {
    }
    
    @Before
    public void setUp() {
        setupJaxbContext(SimulationRequestImpl.class);
        
        SimulationConfigurationImpl configuration = new SimulationConfigurationImpl();
        configuration.setTitle("Test simulation");
        configuration.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        configuration.setSimulationRuns(10000);
        
        request = new SimulationRequestImpl();
        request.setConfiguration(configuration);
        request.setVariableRegistry(TestHelper.makeVariableRegistry(TREE_SIZE));
        request.setFormula(TestHelper.buildNodeTree(TREE_SIZE));
    }
    
    @Test
    public void testRequestMarshalling() {
        SimulationRequestImpl unmarshaled = doMarhshalUnmarshal(request, "target/SimulationRequest.xml");
    }
    
}
