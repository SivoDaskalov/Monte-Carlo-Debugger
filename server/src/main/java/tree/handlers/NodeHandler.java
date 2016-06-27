/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package tree.handlers;

import node.Node;

/**
 *
 * @author sdaskalov
 */
public interface NodeHandler<AbstractNode extends Node> {

    void handle(AbstractNode node);
}
