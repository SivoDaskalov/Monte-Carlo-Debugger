/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.interfaces;

/**
 *
 * @author sdaskalov
 */
public interface SimulationContext {

    int getRunNumber();

    double getVariableValue(int index);

    ValueLogger getValueLogger();
}
