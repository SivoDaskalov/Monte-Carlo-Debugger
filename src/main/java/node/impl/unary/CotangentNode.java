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
public class CotangentNode extends AbstractUnaryNode {

    public CotangentNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return 1 / Math.tan(argument.getValue(context));
    }

}
