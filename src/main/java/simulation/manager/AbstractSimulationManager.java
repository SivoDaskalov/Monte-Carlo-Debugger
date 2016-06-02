/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.manager;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import javafx.util.Pair;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.listeners.SimulationCompletionListener;
import simulation.context.SimulationContextImpl;
import tree.handlers.VariableIndexResolvingHandler;
import tree.loggers.MatrixValueLogger;
import tree.walkers.NodeWalker;
import tree.walkers.ReflectiveNodeWalker;
import variable.registry.StochasticVariableRegistry;
import variable.sampled.SampledVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public abstract class AbstractSimulationManager implements SimulationManager {

    protected static final Logger log = LoggerFactory.getLogger(AbstractSimulationManager.class);

    protected final Node root;
    protected final MatrixValueLogger nodeValueRegistry;
    protected final SimulationContextImpl context;
    protected final Pair<Integer, Integer> runs;
    protected SimulationCompletionListener completionListener;
    protected CountDownLatch progressLatch;

    protected AbstractSimulationManager(Node root, StochasticVariableRegistry variables, int runs) {
        this.root = root;
        this.nodeValueRegistry = new MatrixValueLogger(root, runs);
        SampledVariableRegistry sampledVariableRegistry = new SampledVariableRegistry(variables, runs);
        resolveVariableNodeIndices(root, sampledVariableRegistry);
        this.context = new SimulationContextImpl(sampledVariableRegistry, nodeValueRegistry);
        this.runs = new Pair<>(0, runs - 1);
    }

    protected AbstractSimulationManager(Node root, MatrixValueLogger nodeValueRegistry, SimulationContextImpl context, Pair<Integer, Integer> runs) {
        this.root = root;
        this.nodeValueRegistry = nodeValueRegistry;
        this.context = context;
        this.runs = runs;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void await() {
        run();
        try {
            progressLatch.await();
        } catch (InterruptedException ex) {
            log.error("Simulation interrupted", ex);
        }
    }

    public void setCompletionListener(SimulationCompletionListener listener) {
        this.completionListener = listener;
    }

    private void resolveVariableNodeIndices(Node root, SampledVariableRegistry sampledVariableRegistry) {
        VariableIndexResolvingHandler handler = new VariableIndexResolvingHandler(sampledVariableRegistry);
        NodeWalker walker = new ReflectiveNodeWalker(handler);
        walker.walk(root);
    }

    @Override
    public Map<Integer, Node> getNodeIndex() {
        return nodeValueRegistry.getNodeIndex();
    }

    @Override
    public double[][] getValueRegistry() {
        return nodeValueRegistry.getValueRegistry();
    }
}
