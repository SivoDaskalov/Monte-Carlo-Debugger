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
        sampleRegistry = new double[variables.size()][runs];
        variableIndex = new HashMap<>();
        for (int i = 0; i < variables.size(); i++) {
            StochasticVariable current = variables.get(i);
            variableIndex.put(i, current);
            for (int j = 0; j < runs; j++) {
                sampleRegistry[i][j] = current.sample();
            }
        }
    }

    public Map<Integer, StochasticVariable> getVariableIndex() {
        return variableIndex;
    }
    
    public double getVariableValue(int index, int run) {
        return sampleRegistry[index][run];
    }
}
