/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.service;

/**
 *
 * @author sdaskalov
 */
public class StartService {

    public static void main(String[] args) {
        SimulationServicePublisher.getInstance().publish();
    }
}
