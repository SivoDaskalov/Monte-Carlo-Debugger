/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package jaxb;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import node.Node;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.NodeValues;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class JaxbTestCase {

    private static final Logger log = LoggerFactory.getLogger(JaxbTestCase.class);
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    private void assertEquals(SimulationProperties expected, SimulationProperties actual) {
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getSimulationRuns(), actual.getSimulationRuns());
    }

    private void assertEquals(StochasticVariableRegistry expected, StochasticVariableRegistry actual) {
        Assert.assertArrayEquals(expected.getVariables().toArray(), actual.getVariables().toArray());
    }

    private void assertEquals(Node expected, Node actual) {
        // Asserting only the root node of the formula tree
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getRole(), actual.getRole());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    private void assertEquals(List<NodeValues> expected, List<NodeValues> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            NodeValues exp = expected.get(i);
            NodeValues act = actual.get(i);
            Assert.assertEquals(exp.getNodeId(), act.getNodeId());
            Assert.assertArrayEquals(exp.getValues(), act.getValues(), 0.0);
        }
    }

    protected void assertEquals(SimulationRequest expected, SimulationRequest actual) {
        assertEquals(expected.getProperties(), actual.getProperties());
        assertEquals(expected.getVariableRegistry(), actual.getVariableRegistry());
        assertEquals(expected.getFormula(), actual.getFormula());
    }

    protected void assertEquals(SimulationResponse expected, SimulationResponse actual) {
        assertEquals(expected.getProperties(), actual.getProperties());
        assertEquals(expected.getVariableRegistry(), actual.getVariableRegistry());
        assertEquals(expected.getFormula(), actual.getFormula());
        assertEquals(expected.getValues(), actual.getValues());
    }

    protected <T> T doMarhshalUnmarshal(T source, String url) {
        try {
            marshaller.marshal(source, new File(url));
            marshaller.marshal(source, System.out);
            return (T) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            return null;
        }
    }

    protected void setupJaxbContext(Class clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }
}
