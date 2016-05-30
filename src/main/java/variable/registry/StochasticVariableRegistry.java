/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.registry;

import java.util.List;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
public interface StochasticVariableRegistry {

    public void putVariable(StochasticVariable variable);

    public List<StochasticVariable> getVariables();

}
