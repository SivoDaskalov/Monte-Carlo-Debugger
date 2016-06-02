/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.manager;

import java.util.concurrent.CountDownLatch;
import javafx.util.Pair;
import node.Node;
import simulation.context.SimulationContextImpl;
import simulation.loggers.MatrixValueLogger;
import variable.registry.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class SingleThreadSimulationManager extends AbstractSimulationManager {

    public SingleThreadSimulationManager(Node root, StochasticVariableRegistry variables, int runs) {
        super(root, variables, runs);
    }

    public SingleThreadSimulationManager(
            Node root,
            MatrixValueLogger nodeValueRegistry,
            SimulationContextImpl context,
            Pair<Integer, Integer> runs) {
        super(root, nodeValueRegistry, context, runs);
    }

    @Override
    public void run() {
        // Start a simulation only if one has not been started yet
        if (progressLatch == null) {
            progressLatch = new CountDownLatch(1);
            for (int i = runs.getKey(); i <= runs.getValue(); i++) {
                context.setRunNumber(i);
                root.getValue(context);
            }
            if (completionListener != null) {
                completionListener.notify(this);
            }
            progressLatch.countDown();
        }
    }

}
