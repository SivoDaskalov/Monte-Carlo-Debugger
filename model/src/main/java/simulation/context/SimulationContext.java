/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.context;

import simulation.logger.ValueLogger;

/**
 *
 * @author sdaskalov
 */
public interface SimulationContext {

    Integer getRunNumber();

    Double getVariableValue(int index);

    ValueLogger getValueLogger();
}
