/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.service.SimulationServicePublisher;
import simulation.service.client.SimulationServiceClient;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationServiceClientTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SimulationServiceClientTestCase.class);
    private SimulationServiceClient client;
    private SimulationRequest request;
    private final int runs = 20000;

    @Before
    public void setUp() {
        SimulationServicePublisher.getInstance().publish();
        client = new SimulationServiceClient();
        request = new SimulationRequest(
                new SimulationProperties(
                        "Test simulation",
                        "This simulation has been created by the client in order to test the simulation service endpoint",
                        runs),
                TestHelper.makeVariableRegistry(5),
                TestHelper.buildNodeTree(5));
    }

    @After
    public void tearDown() {
        SimulationServicePublisher.getInstance().stop();
    }

    private SimulationResponse simulate(SimulationRequest request) {
        logger.info("Simulation request \"" + request.getProperties().getTitle() + "\" sent");
        long start = System.currentTimeMillis();
        SimulationResponse response = client.simulate(request);
        logger.info("Simulation response \"" + request.getProperties().getTitle()
                + "\" received after " + (System.currentTimeMillis() - start) + " ms");
        return response;
    }

    @Test
    public void testSimulationService() {
        SimulationResponse response = simulate(request);
        Assert.assertNotNull("Null simulation response", response);
        Assert.assertNotNull("Simulated values list missing", response.getValues());
        Assert.assertFalse("Simulated values missing", response.getValues().isEmpty());
        Assert.assertTrue("Unexpected simulated values count", response.getValues().get(0).getValues().length == runs);
    }
}
