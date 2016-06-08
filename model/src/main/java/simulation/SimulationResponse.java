/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.interfaces.SimulationManager;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimulationResponse extends SimulationConfiguration {

    private NodeValuesWrapper values;

    public SimulationResponse() {
        super();
    }

    public SimulationResponse(SimulationRequest request, SimulationManager manager) {
        this(
                request.getProperties(),
                request.getVariableRegistry(),
                request.getFormula(),
                manager
        );
    }

    public SimulationResponse(SimulationProperties configuration,
            StochasticVariableRegistry variables, Node formula, SimulationManager manager) {
        super(configuration, variables, formula);
        this.values = new NodeValuesWrapper(manager);
    }

    public NodeValuesWrapper getNodeValues() {
        return values;
    }

    public void setNodeValues(NodeValuesWrapper nodeValues) {
        this.values = nodeValues;
    }

}
