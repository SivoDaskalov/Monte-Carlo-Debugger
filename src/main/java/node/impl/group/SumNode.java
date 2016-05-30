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
public class SumNode extends AbstractGroupNode {

    public SumNode() {
        super();
    }

    public SumNode(List<Node> elements) {
        super(elements);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double sum = 0;
        for (Node element : elements) {
            sum += element.getValue(context);
        }
        return sum;
    }

}
