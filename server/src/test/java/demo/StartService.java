/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package demo;

import simulation.service.SimulationServicePublisher;

/**
 *
 * @author sdaskalov
 */
public class StartService {

    public static void main(String[] args) {
        SimulationServicePublisher.getInstance().publish();
    }
}
