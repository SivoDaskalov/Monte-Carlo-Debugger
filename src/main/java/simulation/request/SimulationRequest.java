/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.request;

import node.Node;
import simulation.configuration.SimulationConfiguration;
import variable.registry.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public interface SimulationRequest {

    public SimulationConfiguration getConfiguration();

    public StochasticVariableRegistry getVariableRegistry();

    public Node getFormula();
}
