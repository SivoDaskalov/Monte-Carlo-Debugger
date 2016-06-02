/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.registry;

import java.util.ArrayList;
import java.util.List;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
public class StochasticVariableRegistryImpl implements StochasticVariableRegistry {

    private List<StochasticVariable> variables;

    public StochasticVariableRegistryImpl() {
        variables = new ArrayList<>();
    }

    @Override
    public void putVariable(StochasticVariable variable) {
        variables.add(variable);
    }

    @Override
    public List<StochasticVariable> getVariables() {
        return variables;
    }

    @Override
    public void setVariables(List<StochasticVariable> variables) {
        this.variables = variables;
    }

}
