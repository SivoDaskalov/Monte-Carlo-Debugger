/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.loggers;

import java.util.Map;
import node.Node;
import node.impl.AbstractNode;
import simulation.interfaces.SimulationContext;
import simulation.interfaces.ValueLogger;
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
