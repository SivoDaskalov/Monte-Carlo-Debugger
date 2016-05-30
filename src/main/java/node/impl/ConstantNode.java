/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl;

import node.AbstractNode;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class ConstantNode extends AbstractNode {

    private final double value;

    public ConstantNode(double value, String description) {
        super(description);
        this.value = value;
    }

    public ConstantNode(double value) {
        super();
        this.value = value;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return value;
    }

}
