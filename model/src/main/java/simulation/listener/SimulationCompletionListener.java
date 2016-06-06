/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.listener;

import simulation.manager.SimulationManager;
import simulation.manager.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public interface SimulationCompletionListener {

    void notify(SimulationManager manager);
}
