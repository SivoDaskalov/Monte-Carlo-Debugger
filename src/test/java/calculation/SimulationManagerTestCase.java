/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package calculation;

import node.Node;
import node.impl.VariableNode;
import node.impl.group.ProductNode;
import node.impl.group.SumNode;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.manager.ParallelSimulationManager;
import simulation.manager.SimulationManager;
import simulation.manager.SingleThreadSimulationManager;
import variable.impl.ExponentialVariable;
import variable.impl.GaussianVariable;
import variable.impl.UniformVariable;
import variable.registry.StochasticVariableRegistry;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class SimulationManagerTestCase {

    private static final Logger log = LoggerFactory.getLogger(SimulationManagerTestCase.class);
    private static final int TREE_SIZE_SCALE = 250;

    private static StochasticVariableRegistry makeVariableRegistry() {
        StochasticVariableRegistry variables = new StochasticVariableRegistryImpl();
        for (int i = 0; i < TREE_SIZE_SCALE; i++) {
            double from = i * 10.0;
            double to = i * 10.0 + 10.0;
            variables.putVariable(new GaussianVariable("X" + i, 1L, (from + to) / 2.0, 1.0 / 3.0));
            variables.putVariable(new UniformVariable("Y" + i, 1L, from, to));
            variables.putVariable(new ExponentialVariable("Z" + i, 1L, 1.0));
        }
        return variables;
    }

    private static Node buildNodeTree() {
        SumNode rootNode = new SumNode();
        for (int i = 0; i < TREE_SIZE_SCALE; i++) {
            ProductNode product = new ProductNode();
            product.addNode(new VariableNode("X" + i));
            product.addNode(new VariableNode("Y" + i));
            product.addNode(new VariableNode("Z" + i));
            rootNode.addNode(product);
        }
        return rootNode;
    }

    private Node root;
    private StochasticVariableRegistry registry;
    private int runs;
    private int threadload;

    @Before
    public void setUp() {
        root = buildNodeTree();
        registry = makeVariableRegistry();
//        runs = 100000;
        runs = 10000;
        threadload = 100000 * 1001 / 4;
    }

    @Test
    public void testParallelSimulation() {
        long start = System.currentTimeMillis();
        double[][] results = doTest(new ParallelSimulationManager(root, registry, runs, threadload));
        log.debug("Parallel simulation took " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void testSingleThreadSimulation() {
        long start = System.currentTimeMillis();
        double[][] results = doTest(new SingleThreadSimulationManager(root, registry, runs));
        log.debug("Single-threaded simulation took " + (System.currentTimeMillis() - start) + " ms");
    }

    public double[][] doTest(SimulationManager manager) {
        manager.run();
        manager.await();
        return manager.getValueRegistry();
    }
}

// Before refactoring:
// Parallel TreeSize = 31; Runs = 10000; ThreadLoad = 100000; Time = 225 ms
// SingleThreaded Time = 198 ms
// After refactoring: Parallel 24 ms; SingleThreaded 4 ms
