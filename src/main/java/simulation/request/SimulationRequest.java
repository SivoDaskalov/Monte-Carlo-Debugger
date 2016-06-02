/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.request;

import java.util.List;
import node.Node;
import simulation.configuration.SimulationConfiguration;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
public interface SimulationRequest {

    public SimulationConfiguration getConfiguration();

    public List<StochasticVariable> getVariables();

    public Node getFormula();
}
