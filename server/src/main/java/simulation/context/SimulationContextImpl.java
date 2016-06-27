/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.context;

import simulation.interfaces.SimulationContext;
import simulation.interfaces.ValueLogger;

/**
 *
 * @author sdaskalov
 */
public class SimulationContextImpl implements SimulationContext {

    private final SampledVariableRegistry registry;
    private final ValueLogger logger;
    private int runNumber;

    public SimulationContextImpl(SampledVariableRegistry registry, ValueLogger logger) {
        this.registry = registry;
        this.logger = logger;
    }

    public SimulationContextImpl(SimulationContextImpl context) {
        this.registry = context.getRegistry();
        this.logger = context.getValueLogger();
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    @Override
    public int getRunNumber() {
        return runNumber;
    }

    @Override
    public double getVariableValue(int index) {
        return registry.getVariableValue(index, runNumber);
    }

    @Override
    public ValueLogger getValueLogger() {
        return logger;
    }

    public SampledVariableRegistry getRegistry() {
        return registry;
    }

}
