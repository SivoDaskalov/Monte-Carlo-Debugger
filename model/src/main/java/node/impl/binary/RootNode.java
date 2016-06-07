/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import node.impl.AbstractNode;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "root")
public class RootNode extends AbstractBinaryNode {

    public RootNode() {
        super();
    }

    public RootNode(AbstractNode radicand, AbstractNode index) {
        super();
        arguments[0] = radicand;
        arguments[0].setRole("radicand");
        arguments[1] = index;
        arguments[1].setRole("index");
    }

    public Node getRadicand() {
        return arguments[0];
    }

    public void setRadicand(Node radicand) {
        arguments[0] = radicand;
        arguments[0].setRole("radicand");
    }

    public Node getIndex() {
        return arguments[1];
    }

    public void setIndex(Node index) {
        arguments[1] = index;
        arguments[1].setRole("index");
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.pow(Math.E, Math.log(getRadicand().getValue(context)) / getIndex().getValue(context));
    }

}
