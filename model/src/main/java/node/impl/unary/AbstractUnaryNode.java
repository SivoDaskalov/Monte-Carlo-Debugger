/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package node.impl.unary;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.AbstractNode;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbsoluteNode.class, CosineNode.class, CotangentNode.class,
    InvertNode.class, SineNode.class, TangentNode.class})
public abstract class AbstractUnaryNode extends AbstractNode {

    @XmlAnyElement(lax = true)
    protected Node argument;

    public AbstractUnaryNode() {
    }

    public AbstractUnaryNode(Node argument) {
        super();
        this.argument = argument;
    }

    public Node getArgument() {
        return argument;
    }

    public void setArgument(Node argument) {
        this.argument = argument;
    }

}
