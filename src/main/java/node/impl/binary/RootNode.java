/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import node.AbstractNode;
import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class RootNode extends AbstractNode {

    private final Node radicand;
    private final Node index;

    public RootNode(Node radicant, Node index) {
        super();
        this.radicand = radicant;
        this.index = index;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(Math.E, Math.log(radicand.getValue(context)) / index.getValue(context));
    }

}
