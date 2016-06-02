/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import node.impl.AbstractNode;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "exponentiation")
public class ExponentiationNode extends AbstractNode {

    @XmlAnyElement(lax = true)
    private Node base;
    @XmlAnyElement(lax = true)
    private Node exponent;

    public ExponentiationNode() {
    }

    public ExponentiationNode(Node base, Node exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public Node getBase() {
        return base;
    }

    public void setBase(Node base) {
        this.base = base;
    }

    public Node getExponent() {
        return exponent;
    }

    public void setExponent(Node exponent) {
        this.exponent = exponent;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(base.getValue(context), exponent.getValue(context));
    }

}
