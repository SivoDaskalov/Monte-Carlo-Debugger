/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
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

    public static final String URL = "http://localhost:9999/SimulationService";
    private final Endpoint endpoint;

    public static SimulationServicePublisher getInstance() {
        return instance;
    }

    static {
        instance = new SimulationServicePublisher();
    }

    SimulationServicePublisher() {
        endpoint = Endpoint.create(new SimulationServiceImpl());
    }

    public void publish() {
        endpoint.stop();
        endpoint.publish(URL);
        logger.info("Simulation service started on address " + URL);
    }

    public void stop() {
        endpoint.stop();
        logger.info("Simulation service stopped");
    }
    
    public static void main(String[] args){
        SimulationServicePublisher.getInstance().publish();
    }
}
