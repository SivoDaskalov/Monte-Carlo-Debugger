/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import node.Node;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.NodeValuesWrapper;
import simulation.configuration.SimulationConfiguration;
import simulation.response.SimulationResponseImpl;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class UnmarshalSimulationResponse {

    private static final Logger log = LoggerFactory.getLogger(UnmarshalSimulationResponse.class);
    private Unmarshaller unmarshaller;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponseImpl.class);
    }

    @Test
    public void testResponseUnmarshaling() {
        SimulationResponseImpl response = doUnmarshal("src/main/resources/SimulationResponse.xml");
        SimulationConfiguration configuration = response.getConfiguration();
        Node formula = response.getFormula();
        StochasticVariableRegistryImpl variableRegistry = response.getVariableRegistry();
        NodeValuesWrapper nodeValues = response.getNodeValues();
    }

    protected SimulationResponseImpl doUnmarshal(String url) {
        try {
            return (SimulationResponseImpl) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            return null;
        }
    }

    protected void setupJaxbContext(Class clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }
}
