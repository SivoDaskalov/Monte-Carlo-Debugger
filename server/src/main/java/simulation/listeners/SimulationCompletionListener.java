/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.listeners;

import simulation.interfaces.SimulationManager;
import simulation.interfaces.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public interface SimulationCompletionListener {

    void notify(SimulationManager manager);
}
