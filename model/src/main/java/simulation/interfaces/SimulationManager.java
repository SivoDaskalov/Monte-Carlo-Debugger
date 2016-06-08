/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.interfaces;

import java.util.Map;
import node.Node;

/**
 *
 * @author sdaskalov
 */
public interface SimulationManager extends Runnable {

    public Node getRoot();

    public Map<Integer, Node> getNodeIndex();

    public double[][] getValueRegistry();

    public void await();
}
