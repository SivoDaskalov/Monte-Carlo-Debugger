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
public class DivisionNode extends AbstractNode {

    private final Node dividend;
    private final Node divisor;

    public DivisionNode(Node dividend, Node divisor) {
        super();
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    protected double calculate(SimulationContext context) {
        Double value = dividend.getValue(context) / divisor.getValue(context);
        if (value.isInfinite()) {
            value = Double.NaN;
        }
        return value;
    }

}
