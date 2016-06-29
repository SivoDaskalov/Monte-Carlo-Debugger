/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation;

import java.util.List;
import javafx.util.Pair;
import node.Node;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.manager.ParallelSimulationManager;
import tree.utils.TreeUtilities;

/**
 *
 * @author sdaskalov
 */
public class ThreadWorkloadDivisionTestCase {

    private static final Logger log = LoggerFactory.getLogger(ThreadWorkloadDivisionTestCase.class);

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
        log.info("");
    }

    @Test
    public void testCorrectWorkloadDivision() {
        for (int i = 1; i <= 10; i++) {
            List<Pair<Integer, Integer>> ranges = doTest(100, 1000, 100000 / i);
            printSimulationRanges(ranges);
            Assert.assertEquals(i + (100000 % i > 0 ? 1 : 0), ranges.size());
        }
    }
}
