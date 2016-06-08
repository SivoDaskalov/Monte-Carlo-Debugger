/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "simulation")
public class SimulationRequest extends SimulationConfiguration {

    public SimulationRequest() {
        super();
    }

    public SimulationRequest(SimulationProperties properties, StochasticVariableRegistry variables, Node formula) {
        super(properties, variables, formula);
    }
}
