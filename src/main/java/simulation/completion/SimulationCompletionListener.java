/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.completion;

import simulation.manager.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public interface SimulationCompletionListener {

    void notify(SimulationManager manager);
}
