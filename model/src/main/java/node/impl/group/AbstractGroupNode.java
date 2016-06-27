/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package node.impl.group;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
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

    @XmlAnyElement(lax = true)
    protected List<Node> children;

    public AbstractGroupNode() {
        this(new ArrayList<>());
    }

    public AbstractGroupNode(List<Node> children) {
        super();
        this.children = children;
    }

    @Override
    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

}
