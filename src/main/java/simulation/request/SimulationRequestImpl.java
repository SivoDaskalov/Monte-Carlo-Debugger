/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.request;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import node.impl.AbstractNode;
import simulation.configuration.SimulationConfigurationImpl;
import variable.AbstractVariable;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
@XmlSeeAlso({AbstractNode.class, AbstractVariable.class})
@XmlAccessorType(XmlAccessType.NONE)
public class SimulationRequestImpl implements SimulationRequest {

    @XmlElement(name = "configuration")
    private SimulationConfigurationImpl configuration;

    @XmlElementWrapper(name = "variables")
    @XmlAnyElement(lax = true)
    private List<StochasticVariable> variables;

    @XmlAnyElement(lax = true)
    private Node formula;

    public SimulationRequestImpl() {
    }

    public SimulationRequestImpl(SimulationConfigurationImpl configuration, List<StochasticVariable> variables, Node formula) {
        this.configuration = configuration;
        this.variables = variables;
        this.formula = formula;
    }

    public void setConfiguration(SimulationConfigurationImpl configuration) {
        this.configuration = configuration;
    }

    @Override
    public SimulationConfigurationImpl getConfiguration() {
        return configuration;
    }

    public void setVariables(List<StochasticVariable> variables) {
        this.variables = variables;
    }

    @Override
    public List<StochasticVariable> getVariables() {
        return variables;
    }

    public void setFormula(Node formula) {
        this.formula = formula;
    }

    @Override
    public Node getFormula() {
        return formula;
    }
}
