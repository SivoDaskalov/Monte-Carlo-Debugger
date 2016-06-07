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
@XmlRootElement(name = "logarithm")
public class LogarithmNode extends AbstractNode {

    @XmlAnyElement(lax = true)
    private Node argument;
    @XmlAnyElement(lax = true)
    private Node base;

    public LogarithmNode() {
    }

    public LogarithmNode(AbstractNode argument, AbstractNode base) {
        super();
        this.argument = argument;
        this.argument.setRole("argument");
        this.base = base;
        this.base.setRole("base");
    }

    public Node getArgument() {
        return argument;
    }

    public void setArgument(Node argument) {
        this.argument = argument;
        this.argument.setRole("argument");
    }

    public Node getBase() {
        return base;
    }

    public void setBase(Node base) {
        this.base = base;
        this.base.setRole("base");
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.log(argument.getValue(context)) / Math.log(base.getValue(context));
    }

}
