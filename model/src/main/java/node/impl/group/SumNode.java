/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "sum")
public class SumNode extends AbstractGroupNode {

    public SumNode() {
        super();
    }

    public SumNode(List<Node> children) {
        super(children);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double sum = 0;
        for (Node child : children) {
            sum += child.getValue(context);
        }
        return sum;
    }

}
