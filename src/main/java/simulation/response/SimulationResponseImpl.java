/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.response;

import java.util.List;
import node.Node;
import simulation.configuration.SimulationConfiguration;
import variable.StochasticVariable;

/**
 *
 * @author sdaskalov
 */
public class SimulationResponseImpl implements SimulationResponse {

    private SimulationConfiguration configuration;
    private List<StochasticVariable> variables;
    private Node formula;
    private double[][] valueRegistry;

    public SimulationResponseImpl() {
    }

    @Override
    public SimulationConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(SimulationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<StochasticVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<StochasticVariable> variables) {
        this.variables = variables;
    }

    @Override
    public Node getFormula() {
        return formula;
    }

    public void setFormula(Node formula) {
        this.formula = formula;
    }

    @Override
    public double[][] getValueRegistry() {
        return valueRegistry;
    }

    public void setValueRegistry(double[][] valueRegistry) {
        this.valueRegistry = valueRegistry;
    }

}
