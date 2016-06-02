/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import node.Node;
import simulation.NodeTreeWrapper;
import simulation.configuration.SimulationConfiguration;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.request.SimulationRequest;
import variable.AbstractVariable;
import variable.registry.StochasticVariableRegistry;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
@XmlSeeAlso({NodeTreeWrapper.class, AbstractVariable.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationResponseImpl implements SimulationResponse {

    private SimulationConfigurationImpl configuration;

    private StochasticVariableRegistryImpl variables;

    @XmlElement(name = "formula")
    private final NodeTreeWrapper formulaWrapper;

    @XmlTransient
    private double[][] valueRegistry;

    public SimulationResponseImpl() {
        this.formulaWrapper = new NodeTreeWrapper();
    }

    public SimulationResponseImpl(SimulationConfigurationImpl configuration,
            StochasticVariableRegistryImpl variables, Node formula, double[][] valueRegistry) {
        this.configuration = configuration;
        this.variables = variables;
        this.formulaWrapper = new NodeTreeWrapper(formula);
        this.valueRegistry = valueRegistry;
    }

    public SimulationResponseImpl(SimulationRequest request, double[][] valueRegistry) {
        configuration = (SimulationConfigurationImpl) request.getConfiguration();
        variables = (StochasticVariableRegistryImpl) request.getVariableRegistry();
        formulaWrapper = new NodeTreeWrapper(request.getFormula());
        this.valueRegistry = valueRegistry;
    }

    @Override
    public SimulationConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(SimulationConfigurationImpl configuration) {
        this.configuration = configuration;
    }

    @Override
    public StochasticVariableRegistry getVariableRegistry() {
        return variables;
    }

    public void setVariables(StochasticVariableRegistryImpl variables) {
        this.variables = variables;
    }

    @Override
    public Node getFormula() {
        return formulaWrapper.getRoot();
    }

    public void setFormula(Node formula) {
        this.formulaWrapper.setRoot(formula);
    }

    @Override
    public double[][] getValueRegistry() {
        return valueRegistry;
    }

    public void setValueRegistry(double[][] valueRegistry) {
        this.valueRegistry = valueRegistry;
    }

}
