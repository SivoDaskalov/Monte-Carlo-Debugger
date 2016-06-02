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
import node.Node;
import simulation.manager.SimulationManager;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public class NodeValuesWrapper {

    @XmlElement(name = "node")
    List<NodeValues> values;

    public NodeValuesWrapper() {
    }

    public NodeValuesWrapper(SimulationManager manager) {
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
