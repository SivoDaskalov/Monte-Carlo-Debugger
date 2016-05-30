/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.unary;

import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class AbsoluteNode extends AbstractUnaryNode {

    public AbsoluteNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.abs(argument.getValue(context));
    }

}
