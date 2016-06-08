/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.loggers;

import simulation.interfaces.ValueLogger;
import java.util.Map;
import node.impl.AbstractNode;
import node.Node;
import simulation.interfaces.SimulationContext;
import tree.utils.TreeUtilities;
import tree.utils.TreeUtilities;

/**
 *
 * @author sdaskalov
 */
public class MatrixValueLogger implements ValueLogger<AbstractNode> {

    private final Map<Integer, Node> nodeIndex;
    private final double[][] valueRegistry;

    public MatrixValueLogger(Node root, int runs) {
        nodeIndex = TreeUtilities.resolveNodeIndices(root);
        valueRegistry = new double[nodeIndex.size()][runs];
    }

    public Map<Integer, Node> getNodeIndex() {
        return nodeIndex;
    }

    public double[][] getValueRegistry() {
        return valueRegistry;
    }

    @Override
    public void logValue(AbstractNode node, Double value, SimulationContext context) {
        valueRegistry[node.getNodeIndex()][context.getRunNumber()] = value;
    }

}
