/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl;

import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.binary.AbstractBinaryNode;
import node.impl.group.AbstractGroupNode;
import node.impl.unary.AbstractUnaryNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbstractUnaryNode.class, AbstractBinaryNode.class,
    AbstractGroupNode.class, ConstantNode.class, VariableNode.class,})
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractNode implements Node {

    protected static final Logger log = LoggerFactory.getLogger(AbstractNode.class);
    private static final AtomicLong idGenerator = new AtomicLong();

    @XmlAttribute(name = "nodeId")
    private String id;
    @XmlAttribute
    private String role;
    @XmlAttribute
    private String description;
    @XmlAttribute(name = "index")
    private Integer nodeIndex;

    public AbstractNode() {
        this(null);
    }

    public AbstractNode(String description) {
        id = String.valueOf(idGenerator.getAndIncrement());
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(Integer nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    @Override
    public double getValue(SimulationContext context) {
        double value = calculate(context);
        context.getValueLogger().logValue(this, value, context);
        return value;
    }

    abstract protected double calculate(SimulationContext context);

}
