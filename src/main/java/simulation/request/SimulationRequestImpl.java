/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.request;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import node.Node;
import simulation.configuration.SimulationConfigurationImpl;
import variable.StochasticVariable;
import variable.impl.ExponentialVariable;
import variable.impl.GaussianVariable;
import variable.impl.UniformVariable;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationRequestImpl implements SimulationRequest {

    private SimulationConfigurationImpl configuration;

    @XmlElementWrapper(name = "variables")
    @XmlElements({
        @XmlElement(name = "gaussian", type = GaussianVariable.class),
        @XmlElement(name = "uniform", type = UniformVariable.class),
        @XmlElement(name = "exponential", type = ExponentialVariable.class)})
    private List<StochasticVariable> variables;

    @XmlTransient
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
