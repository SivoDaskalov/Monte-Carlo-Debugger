/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package model;

import node.Node;

/**
 *
 * @author sdaskalov
 */
public class DebuggedNode {

    private final Node node;
    private boolean valueVisible;

    public DebuggedNode(Node node) {
        this.node = node;
        valueVisible = false;
    }

    public Node getNode() {
        return node;
    }

    public boolean isValueVisible() {
        return valueVisible;
    }

    public void setValueVisible(boolean valueVisible) {
        this.valueVisible = valueVisible;
    }

}
