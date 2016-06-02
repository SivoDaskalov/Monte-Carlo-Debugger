/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import node.Node;
import org.junit.Before;
import org.junit.Test;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.manager.SimulationManager;
import simulation.manager.SingleThreadSimulationManager;
import simulation.response.SimulationResponseImpl;
import testutils.BuildHelper;
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
        configuration.setSimulationRuns(100);

        Node formula = BuildHelper.buildNodeTree(TREE_SIZE);
        StochasticVariableRegistryImpl variables = BuildHelper.makeVariableRegistry(TREE_SIZE);

        SimulationManager manager = new SingleThreadSimulationManager(
                formula,
                variables,
                configuration.getSimulationRuns());
        manager.run();
        manager.await();

        response = new SimulationResponseImpl();
        response.setConfiguration(configuration);
        response.setVariables(variables);
        response.setFormula(manager.getRoot());
        response.setValueRegistry(manager.getValueRegistry());
    }

    @Test
    public void testResponseMarshalling() {
        SimulationResponseImpl unmarshalled = doMarhshalUnmarshal(response, "target/SimulationResponse.xml");
    }
}
