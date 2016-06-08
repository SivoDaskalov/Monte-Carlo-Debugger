/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.interfaces;

/**
 *
 * @author sdaskalov
 */
public interface SimulationContext {

    Integer getRunNumber();

    Double getVariableValue(int index);

    ValueLogger getValueLogger();
}
