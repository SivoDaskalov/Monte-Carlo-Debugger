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
public class ExponentiationNode extends AbstractNode {

    private final Node base;
    private final Node exponent;

    public ExponentiationNode(Node base, Node exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(base.getValue(context), exponent.getValue(context));
    }

}
