/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.request.SimulationRequestImpl;
import testutils.BuildHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationRequestJaxbTestCase {

    private static final Logger log = LoggerFactory.getLogger(SimulationRequestJaxbTestCase.class);
    private final int TREE_SIZE = 3;
    private SimulationRequestImpl request;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

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
                "This configuration exists to test the marshalling and unmarshalling of requests");
        configuration.setSimulationRuns(10000);

        request = new SimulationRequestImpl();
        request.setConfiguration(configuration);
        request.setVariables(BuildHelper.makeVariableRegistry(TREE_SIZE).getVariables());
        request.setFormula(BuildHelper.buildNodeTree(TREE_SIZE));
    }

    @Test
    public void testRequestMarshalling() {
        SimulationRequestImpl unmarshalled = doMarhshalUnmarshal(request, "target/SimulationRequest.xml");
    }

    private <T> T doMarhshalUnmarshal(T source, String url) {
        try {
            marshaller.marshal(source, new File(url));
            marshaller.marshal(source, System.out);
            return (T) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            return null;
        }
    }

}
