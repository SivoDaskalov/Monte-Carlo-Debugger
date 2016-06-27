/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
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
