/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.listeners;

import simulation.interfaces.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public interface SimulationCompletionListener {

    void notify(SimulationManager manager);
}
