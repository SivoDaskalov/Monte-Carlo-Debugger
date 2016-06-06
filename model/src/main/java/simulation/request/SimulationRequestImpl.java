/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.NodeTreeWrapper;
import simulation.configuration.SimulationConfiguration;
import simulation.configuration.SimulationConfigurationImpl;
import variable.registry.StochasticVariableRegistry;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationRequestImpl implements SimulationRequest {

    private SimulationConfigurationImpl configuration;

    private StochasticVariableRegistryImpl variables;

    @XmlElement(name = "formula")
    private final NodeTreeWrapper formulaWrapper;

    public SimulationRequestImpl() {
        formulaWrapper = new NodeTreeWrapper();
    }

    public SimulationRequestImpl(SimulationConfigurationImpl configuration, StochasticVariableRegistryImpl variables, Node formula) {
        this.configuration = configuration;
        this.variables = variables;
        this.formulaWrapper = new NodeTreeWrapper(formula);
    }

    public void setConfiguration(SimulationConfigurationImpl configuration) {
        this.configuration = configuration;
    }

    @Override
    public SimulationConfiguration getConfiguration() {
        return configuration;
    }

    public void setVariableRegistry(StochasticVariableRegistryImpl variables) {
        this.variables = variables;
    }

    @Override
    public StochasticVariableRegistry getVariableRegistry() {
        return variables;
    }

    public void setFormula(Node formula) {
        this.formulaWrapper.setRoot(formula);
    }

    @Override
    public Node getFormula() {
        return formulaWrapper.getRoot();
    }
}
