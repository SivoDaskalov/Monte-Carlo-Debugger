/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "node")
    private List<NodeValues> values;

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
        values = new ArrayList<>();
        double[][] valueRegistry = manager.getValueRegistry();
        for (Map.Entry<Integer, Node> entry : manager.getNodeIndex().entrySet()) {
            values.add(
                    new NodeValues(
                            entry.getValue().getId(),
                            valueRegistry[entry.getKey()]
                    )
            );
        }
    }

    public List<NodeValues> getValues() {
        return values;
    }

    public void setValues(List<NodeValues> values) {
        this.values = values;
    }

}
