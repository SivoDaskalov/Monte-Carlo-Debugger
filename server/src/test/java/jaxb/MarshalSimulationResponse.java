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
    private Marshaller marshaller;
    private SimulationResponse response;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponse.class);

        SimulationProperties configuration = new SimulationProperties();
        configuration.setTitle("Test simulation");
        configuration.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        configuration.setSimulationRuns(10000);

        Node formula = TestHelper.buildNodeTree(TREE_SIZE);
        StochasticVariableRegistry variableRegistry = TestHelper.makeVariableRegistry(TREE_SIZE);

        ParallelSimulationManager simulationManager = new ParallelSimulationManager(formula, variableRegistry, 100, 10000);
        simulationManager.run();
        simulationManager.await();

        response = new SimulationResponse(configuration, variableRegistry, formula, simulationManager);
    }

    @Test
    public void testResponseMarshaling() {
        doMarshal(response, "target/SimulationResponse.xml");
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
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }
}
