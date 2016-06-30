/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.service;

import javax.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
public class SimulationServicePublisher {

    private static final Logger logger = LoggerFactory.getLogger(SimulationServicePublisher.class);
    private static final SimulationServicePublisher instance;

    public String url;
    private final Endpoint endpoint;

    public static SimulationServicePublisher getInstance() {
        return instance;
    }

    static {
        instance = new SimulationServicePublisher();
        instance.setPort(9999);
    }

    SimulationServicePublisher() {
        endpoint = Endpoint.create(new SimulationServiceImpl());
    }

    public SimulationServicePublisher setPort(int port) {
        url = "http://localhost:" + port + "/SimulationService";
        return this;
    }

    public void publish() {
        endpoint.stop();
        endpoint.publish(url);
        logger.info("Simulation service started on address " + url);
    }

    public void stop() {
        endpoint.stop();
        logger.info("Simulation service stopped");
    }

}
