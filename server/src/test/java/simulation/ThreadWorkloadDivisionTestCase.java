/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation;

import tree.utils.TreeUtilities;
import simulation.manager.ParallelSimulationManager;
import java.util.List;
import javafx.util.Pair;
import node.Node;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
public class ThreadWorkloadDivisionTestCase {

    private static final Logger log = LoggerFactory.getLogger(ThreadWorkloadDivisionTestCase.class);

    public ThreadWorkloadDivisionTestCase() {
    }

    private List<Pair<Integer, Integer>> doTest(Node root, int runs, int threadLoad) {
        return doTest(TreeUtilities.getTreeSize(root), runs, threadLoad);
    }

    private List<Pair<Integer, Integer>> doTest(int treeSize, int runs, int threadLoad) {
        int totalWorkload = runs * treeSize;
        int poolSize = ParallelSimulationManager.calculateThreadPoolSize(totalWorkload, threadLoad);
        return ParallelSimulationManager.calculateSimulationRanges(runs, poolSize);
    }

    private void printSimulationRanges(List<Pair<Integer, Integer>> ranges) {
        for (Pair<Integer, Integer> range : ranges) {
            log.info(range.getKey() + " - " + range.getValue());
        }
    }

    @Test
    public void testCorrectWorkloadDivision() {
        printSimulationRanges(doTest(100, 1000, 10000));
    }
}
