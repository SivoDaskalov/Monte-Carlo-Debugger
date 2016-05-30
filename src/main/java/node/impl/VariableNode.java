/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl;

import javax.xml.bind.annotation.XmlRootElement;
import node.AbstractNode;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "variable")
public class VariableNode extends AbstractNode {

    private final String name;
    private int index;

    public VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    protected double calculate(SimulationContext context) {
        return context.getVariableValue(index);
    }

}
