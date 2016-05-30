/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node;

import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public abstract class AbstractNode implements Node {

    protected static final Logger log = LoggerFactory.getLogger(AbstractNode.class);
    private static final AtomicLong idGenerator = new AtomicLong();

    protected final String id;
    private String description;
    private Integer nodeIndex;

    public AbstractNode() {
        this("");
    }

    public AbstractNode(String description) {
        id = String.valueOf(idGenerator.getAndIncrement());
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

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
