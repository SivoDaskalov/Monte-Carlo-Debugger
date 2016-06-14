/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import node.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationProperties;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import simulation.manager.ParallelSimulationManager;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class MarshalSimulationResponse {

    private static final Logger log = LoggerFactory.getLogger(MarshalSimulationResponse.class);
    private final int TREE_SIZE = 3;
    private final int TREE_DEPTH = 3;
    private final int RUNS = 100;
    private Marshaller marshaller;
    private SimulationResponse defaultResponse;
    private SimulationResponse deepTreeResponse;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponse.class);

        SimulationProperties configuration = new SimulationProperties();
        configuration.setTitle("Test simulation");
        configuration.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        configuration.setSimulationRuns(RUNS);

        StochasticVariableRegistry variableRegistry = TestHelper.makeVariableRegistry(TREE_SIZE);

        Node defaultFormula = TestHelper.buildNodeTree(TREE_SIZE);
        Node deepTreeFormula = TestHelper.buildDeepNodeTree(TREE_DEPTH, TREE_SIZE);

        defaultResponse = doSimulate(configuration, defaultFormula, variableRegistry);
        deepTreeResponse = doSimulate(configuration, deepTreeFormula, variableRegistry);
    }

    @Test
    public void testResponseMarshaling() {
        doMarshal(defaultResponse, "target/DefaultTreeResponse.xml");
        doMarshal(deepTreeResponse, "target/DeepTreeResponse.xml");
    }

    protected SimulationResponse doSimulate(SimulationProperties configuration, Node root, StochasticVariableRegistry variables) {
        ParallelSimulationManager simulationManager = new ParallelSimulationManager(root, variables, configuration.getSimulationRuns(), 10000);
        simulationManager.run();
        simulationManager.await();
        return new SimulationResponse(configuration, variables, root, simulationManager);
    }

    protected void doMarshal(SimulationResponse response, String url) {
        try {
            marshaller.marshal(response, new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            Assert.fail();
        }
    }

    protected void setupJaxbContext(Class clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }
}
