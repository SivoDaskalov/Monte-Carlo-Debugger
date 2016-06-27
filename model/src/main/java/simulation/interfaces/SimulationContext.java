/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
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
