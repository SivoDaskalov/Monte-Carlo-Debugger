/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import node.impl.AbstractNode;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "exponentiation")
public class ExponentiationNode extends AbstractBinaryNode {

    public ExponentiationNode() {
        super();
    }

    public ExponentiationNode(AbstractNode base, AbstractNode exponent) {
        super();
        arguments[0] = base;
        arguments[0].setRole("base");
        arguments[1] = exponent;
        arguments[1].setRole("exponent");

    }

    public Node getBase() {
        return arguments[0];
    }

    public void setBase(Node base) {
        arguments[0] = base;
        arguments[0].setRole("base");
    }

    public Node getExponent() {
        return arguments[1];
    }

    public void setExponent(Node exponent) {
        arguments[1] = exponent;
        arguments[1].setRole("exponent");
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(getBase().getValue(context), getExponent().getValue(context));
    }

}
