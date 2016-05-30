/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.sampled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import variable.StochasticVariable;
import variable.registry.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class SampledVariableRegistry {

    private final Map<Integer, StochasticVariable> variableIndex;
    private final double[][] sampleRegistry;

    public SampledVariableRegistry(StochasticVariableRegistry variableRegistry, int runs) {
        List<StochasticVariable> variables = variableRegistry.getVariables();
        variableIndex = new HashMap<>();
        sampleRegistry = new double[variables.size()][];
        for (int i = 0; i < variables.size(); i++) {
            StochasticVariable current = variables.get(i);
            variableIndex.put(i, current);
            sampleRegistry[i] = current.sample(runs);
        }
    }

    public Map<Integer, StochasticVariable> getVariableIndex() {
        return variableIndex;
    }

    public double getVariableValue(int index, int run) {
        return sampleRegistry[index][run];
    }
}
