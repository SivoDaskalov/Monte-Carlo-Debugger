/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import node.Node;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.interfaces.SimulationManager;
import simulation.manager.ParallelSimulationManager;
import simulation.manager.SingleThreadSimulationManager;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class SimulationManagerTestCase {

    private static final Logger log = LoggerFactory.getLogger(SimulationManagerTestCase.class);
    private static final int TREE_SIZE_SCALE = 100;

    private static StochasticVariableRegistry makeVariableRegistry() {
        return TestHelper.makeVariableRegistry(TREE_SIZE_SCALE);
    }

    private static Node buildNodeTree() {
        return TestHelper.buildNodeTree(TREE_SIZE_SCALE);
    }

    private Node root;
    private StochasticVariableRegistry registry;
    private int runs;
    private int threadload;

    @Before
    public void setUp() {
        root = buildNodeTree();
        registry = makeVariableRegistry();
        runs = 10000;
        threadload = 10000 * 1001 / 4;
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
