/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.AbstractNode;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({AbstractNode.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SimulationConfiguration {

    private SimulationProperties properties;

    private StochasticVariableRegistry variables;

    @XmlAnyElement(lax = true)
    @XmlElementWrapper(name = "formula")
    private final Node[] formula;

    public SimulationConfiguration() {
        formula = new Node[1];
    }

    public SimulationConfiguration(SimulationProperties properties, StochasticVariableRegistry variables, Node formula) {
        this.properties = properties;
        this.variables = variables;
        this.formula = new Node[1];
        this.formula[0] = formula;
    }

    public SimulationProperties getProperties() {
        return properties;
    }

    public void setProperties(SimulationProperties properties) {
        this.properties = properties;
    }

    public void setVariableRegistry(StochasticVariableRegistry variables) {
        this.variables = variables;
    }

    public StochasticVariableRegistry getVariableRegistry() {
        return variables;
    }

    public void setFormula(Node formula) {
        this.formula[0] = formula;
    }

    public Node getFormula() {
        return formula[0];
    }
}
