/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package tree.handlers;

import node.Node;

/**
 *
 * @author sdaskalov
 */
public class NodeCountingHandler implements NodeHandler {

    private Integer count;

    public NodeCountingHandler() {
        count = 0;
    }

    public Integer getNodeCount() {
        return count;
    }

    @Override
    public void handle(Node node) {
        count++;
    }

}
