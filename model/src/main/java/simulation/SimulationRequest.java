/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
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
