/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.unary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "cosine")
public class CosineNode extends AbstractUnaryNode {

    public CosineNode() {
    }

    public CosineNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.cos(argument.getValue(context));
    }

}
