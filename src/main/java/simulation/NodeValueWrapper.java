/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import java.util.Map;
import node.Node;
import simulation.manager.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public class NodeValueWrapper {

    private Map<Integer, Node> nodeIndex;
    private double[][] valueRegistry;

    public NodeValueWrapper(SimulationManager manager) {
        nodeIndex = manager.getNodeIndex();
        valueRegistry = manager.getValueRegistry();
    }

}
