/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.context;

import simulation.manager.SampledVariableRegistry;
import simulation.logger.ValueLogger;

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

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    @Override
    public Integer getRunNumber() {
        return runNumber;
    }

    @Override
    public Double getVariableValue(int index) {
        return registry.getVariableValue(index, runNumber);
    }

    @Override
    public ValueLogger getValueLogger() {
        return logger;
    }

}
