/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.manager;

import java.util.concurrent.CountDownLatch;
import javafx.util.Pair;
import node.Node;
import simulation.StochasticVariableRegistry;
import simulation.context.SimulationContextImpl;
import simulation.loggers.MatrixValueLogger;

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
            MatrixValueLogger valueRegistry,
            SimulationContextImpl context,
            Pair<Integer, Integer> runs) {
        super(root, valueRegistry, context, runs);
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
            progressLatch.countDown();
            if (completionListener != null) {
                completionListener.notify(this);
            }
        }
    }

}
