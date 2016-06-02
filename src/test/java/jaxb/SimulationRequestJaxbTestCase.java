/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.Before;
import org.junit.Test;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.request.SimulationRequestImpl;
import testutils.BuildHelper;

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
        try {
            JAXBContext context = JAXBContext.newInstance(SimulationRequestImpl.class);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }

        SimulationConfigurationImpl configuration = new SimulationConfigurationImpl();
        configuration.setTitle("Test simulation");
        configuration.setDescription(
                "This configuration exists to test the marshaling and unmarshaling of requests");
        configuration.setSimulationRuns(10000);

        request = new SimulationRequestImpl();
        request.setConfiguration(configuration);
        request.setVariableRegistry(BuildHelper.makeVariableRegistry(TREE_SIZE));
        request.setFormula(BuildHelper.buildNodeTree(TREE_SIZE));
    }

    @Test
    public void testRequestMarshalling() {
        SimulationRequestImpl unmarshalled = doMarhshalUnmarshal(request, "target/SimulationRequest.xml");
    }

}
