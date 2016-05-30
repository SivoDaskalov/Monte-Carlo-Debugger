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
public class LogarithmNode extends AbstractNode {

    private final Node argument;
    private final Node base;

    public LogarithmNode(Node argument, Node base) {
        super();
        this.argument = argument;
        this.base = base;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.log(argument.getValue(context)) / Math.log(base.getValue(context));
    }

}
