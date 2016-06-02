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
@XmlRootElement(name = "cotangent")
public class CotangentNode extends AbstractUnaryNode {

    public CotangentNode() {
    }

    public CotangentNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return 1 / Math.tan(argument.getValue(context));
    }

}
