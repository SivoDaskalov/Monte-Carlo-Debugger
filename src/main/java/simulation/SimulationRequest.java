/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import java.util.List;
import node.Node;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
public interface SimulationRequest {

    public void setConfiguration(SimulationConfiguration configuration);

    public SimulationConfiguration getConfiguration();

    public void setVariables(List<StochasticVariable> variables);

    public List<StochasticVariable> getVariables();

    public void setFormula(Node root);

    public Node getFormula();
}
