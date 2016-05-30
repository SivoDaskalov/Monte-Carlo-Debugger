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
public class TangentNode extends AbstractUnaryNode {

    public TangentNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.tan(argument.getValue(context));
    }

}
