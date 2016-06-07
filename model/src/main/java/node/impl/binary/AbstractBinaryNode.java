/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.AbstractNode;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({DivisionNode.class, ExponentiationNode.class,
    LogarithmNode.class, RootNode.class})
public abstract class AbstractBinaryNode extends AbstractNode {

    @XmlElementWrapper(name = "arguments")
    @XmlAnyElement(lax = true)
    protected Node[] arguments;

    public AbstractBinaryNode() {
        super();
        arguments = new Node[2];
    }

    public Node[] getArguments() {
        return arguments;
    }

    public void setArguments(Node[] arguments) {
        this.arguments = arguments;
    }

}
