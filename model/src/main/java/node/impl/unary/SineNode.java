/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.unary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "sine")
public class SineNode extends AbstractUnaryNode {

    public SineNode() {
    }

    public SineNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.sin(argument.getValue(context));
    }

}
