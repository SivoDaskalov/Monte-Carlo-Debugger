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
@XmlRootElement(name = "root")
public class RootNode extends AbstractNode {

    @XmlAnyElement(lax = true)
    private Node radicand;
    @XmlAnyElement(lax = true)
    private Node index;

    public RootNode() {
    }

    public RootNode(Node radicant, Node index) {
        super();
        this.radicand = radicant;
        this.index = index;
    }

    public Node getRadicand() {
        return radicand;
    }

    public void setRadicand(Node radicand) {
        this.radicand = radicand;
    }

    public Node getIndex() {
        return index;
    }

    public void setIndex(Node index) {
        this.index = index;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(Math.E, Math.log(radicand.getValue(context)) / index.getValue(context));
    }

}
