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
public class AverageNode extends AbstractGroupNode {

    public AverageNode() {
        super();
    }

    public AverageNode(List<Node> elements) {
        super(elements);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double sum = 0;
        for (Node child : elements) {
            sum += child.getValue(context);
        }
        return sum / elements.size();
    }

}
