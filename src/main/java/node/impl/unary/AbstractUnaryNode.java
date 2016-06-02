/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.unary;

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
