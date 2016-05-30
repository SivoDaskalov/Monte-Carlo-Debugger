/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import java.util.List;

import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class MaxNode extends AbstractGroupNode {

    public MaxNode() {
        super();
    }

    public MaxNode(List<Node> elements) {
        super(elements);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double max = Double.MIN_VALUE;
        for (Node element : elements) {
            double current = element.getValue(context);
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

}
