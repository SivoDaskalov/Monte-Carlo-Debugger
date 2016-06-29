/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package statistics;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import node.Node;
import node.impl.VariableNode;
import node.impl.group.SumNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import simulation.interfaces.SimulationManager;
import simulation.manager.ParallelSimulationManager;
import variable.impl.UniformVariable;

/**
 *
 * @author sdaskalov
 */
public class ResponseXmlSizeTestCase {

    private static final Logger log = LoggerFactory.getLogger(ResponseXmlSizeTestCase.class);
    private static final int threadLoad = 100000 * 1000 / 4;
    private Marshaller marshaller;
    private File file;

    @Before
    public void setUp() {
        setupJaxbContext(SimulationResponse.class);
        file = new File("target/Response.xml");
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

    protected long getSizeInMB(SimulationResponse response) {
        try {
            marshaller.marshal(response, file);
            long size = file.length() / 1048576;
            file.delete();
            return size;
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            Assert.fail();
            return 0;
        }
    }

    protected SimulationResponse simulate(SimulationRequest request) {
        SimulationManager simulationManager = new ParallelSimulationManager(
                request.getFormula(),
                request.getVariableRegistry(),
                request.getProperties().getSimulationRuns(),
                threadLoad);
        simulationManager.run();
        simulationManager.await();
        return new SimulationResponse(request, simulationManager);
    }

    private SimulationProperties makeProperties(int simulationRuns) {
        return new SimulationProperties("Title", "Description", simulationRuns);
    }

    private StochasticVariableRegistry makeVariables() {
        StochasticVariableRegistry variables = new StochasticVariableRegistry();
        variables.addVariable(new UniformVariable("X"));
        return variables;
    }

    private Node makeFormula(int size) {
        if (size == 0) {
            return null;
        }
        SumNode sum = new SumNode();
        for (int i = 1; i < size; i++) {
            sum.addChild(new VariableNode("X"));
        }
        return sum;
    }

    private SimulationRequest makeRequest(int treeSize, int runs) {
        return new SimulationRequest(makeProperties(runs), makeVariables(), makeFormula(treeSize));
    }

    @Ignore
    @Test
    public void logResponseXmlSize() {
        int treeSize = 1000;
        int runsStep = 1000;
        int runsMax = 20000;

        System.out.println(String.format("%s\t%s\t%s", "Runs", "Time [ms]", "Size [MB]"));
        for (int i = runsStep; i <= runsMax; i += runsStep) {
            SimulationRequest request = makeRequest(treeSize, i);
            SimulationResponse response = simulate(request);
            long start = System.currentTimeMillis();
            long size = getSizeInMB(response);
            long time = System.currentTimeMillis() - start;
            System.out.println(String.format("%s\t%s\t%s", i, time, size));
        }
    }
}
