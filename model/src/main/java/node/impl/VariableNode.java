/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "variable")
public class VariableNode extends AbstractNode {

    @XmlAttribute
    private String name;
    private int variableIndex;

    public VariableNode() {
    }

    public VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    public void setVariableIndex(int index) {
        this.variableIndex = index;
    }

    @Override
    protected double calculate(SimulationContext context) {
        return context.getVariableValue(variableIndex);
    }

}
