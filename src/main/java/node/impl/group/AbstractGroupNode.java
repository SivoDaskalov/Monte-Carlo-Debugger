/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.GroupNode;
import node.Node;
import node.impl.AbstractNode;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({MaxNode.class, MinNode.class,
    AverageNode.class, ProductNode.class, SumNode.class})
public abstract class AbstractGroupNode extends AbstractNode implements GroupNode {

    protected List<Node> elements;

    public AbstractGroupNode() {
        this(new ArrayList<>());
    }

    public AbstractGroupNode(List<Node> elements) {
        super();
        this.elements = elements;
    }

    @Override
    public void addNode(Node child) {
        elements.add(child);
    }

    public List<Node> getElements() {
        return elements;
    }

    public void setElements(List<Node> elements) {
        this.elements = elements;
    }

}
