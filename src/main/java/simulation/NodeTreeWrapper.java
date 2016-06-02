/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.AbstractNode;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbstractNode.class})
@XmlAccessorType(XmlAccessType.NONE)
public class NodeTreeWrapper {

    @XmlAnyElement(lax = true)
    private Node root;

    public NodeTreeWrapper() {
    }

    public NodeTreeWrapper(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

}
