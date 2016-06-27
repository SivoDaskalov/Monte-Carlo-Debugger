/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.interfaces;

import node.Node;

/**
 *
 * @author sdaskalov
 */
public interface ValueLogger<AbstractNode extends Node> {

    void logValue(AbstractNode node, Double value, SimulationContext context);
}
