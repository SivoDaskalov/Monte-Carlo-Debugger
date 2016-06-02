/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.loggers;

import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public interface ValueLogger<AbstractNode extends Node> {

    void logValue(AbstractNode node, Double value, SimulationContext context);
}
