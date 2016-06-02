/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import node.Node;
import simulation.NodeTreeWrapper;
import simulation.NodeValuesWrapper;
import simulation.configuration.SimulationConfiguration;
import simulation.configuration.SimulationConfigurationImpl;
import simulation.manager.SimulationManager;
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

    @XmlElement(name = "values")
    private NodeValuesWrapper nodeValues;

    public SimulationResponseImpl() {
        this.formulaWrapper = new NodeTreeWrapper();
    }

    public SimulationResponseImpl(SimulationRequest request, SimulationManager manager) {
        this(
                (SimulationConfigurationImpl) request.getConfiguration(),
                (StochasticVariableRegistryImpl) request.getVariableRegistry(),
                request.getFormula(),
                manager
        );
    }

    public SimulationResponseImpl(SimulationConfigurationImpl configuration,
            StochasticVariableRegistryImpl variables, Node formula, SimulationManager manager) {
        this.configuration = configuration;
        this.variables = variables;
        this.formulaWrapper = new NodeTreeWrapper(formula);
        this.nodeValues = new NodeValuesWrapper(manager);
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
    public NodeValuesWrapper getNodeValues() {
        return nodeValues;
    }

    public void setNodeValues(NodeValuesWrapper nodeValues) {
        this.nodeValues = nodeValues;
    }

}
