/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl;

import javax.xml.bind.annotation.XmlRootElement;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "constant")
public class ConstantNode extends AbstractNode {

    private double value;

    public ConstantNode() {
    }

    public ConstantNode(double value, String description) {
        super(description);
        this.value = value;
    }

    public ConstantNode(double value) {
        super();
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    protected double calculate(SimulationContext context) {
        return value;
    }

}
