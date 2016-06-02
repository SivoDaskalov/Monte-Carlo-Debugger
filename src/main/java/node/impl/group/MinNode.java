/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "min")
public class MinNode extends AbstractGroupNode {

    public MinNode() {
        super();
    }

    public MinNode(List<Node> elements) {
        super(elements);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double min = Double.MAX_VALUE;
        for (Node element : elements) {
            double current = element.getValue(context);
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

}
