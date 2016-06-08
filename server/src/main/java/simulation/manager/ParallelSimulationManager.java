/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javafx.util.Pair;
import node.Node;
import simulation.listeners.CompositeCompletionListener;
import simulation.listeners.LatchLoweringCompletionListener;
import tree.utils.TreeUtilities;
import simulation.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class ParallelSimulationManager extends AbstractSimulationManager {

    public static List<Pair<Integer, Integer>> calculateSimulationRanges(int runs, int poolSize) {
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        int defaultSimulationRuns = runs / poolSize;
        int hardworkingThreads = runs % poolSize; // Some of the threads need to do one additional simulation

        int workerRangeStart = 0;
        for (int i = 0; i < poolSize; i++) {
            int workerSimulationRuns
                    = defaultSimulationRuns + (i < hardworkingThreads ? 1 : 0);
            ranges.add(new Pair<>(workerRangeStart, workerRangeStart + workerSimulationRuns - 1));
            workerRangeStart += workerSimulationRuns;
        }

        return ranges;
    }

    public static int calculateThreadPoolSize(int totalWorkload, int threadWorkload) {
        int poolSize;
        for (poolSize = 1; poolSize * threadWorkload < totalWorkload; poolSize++) {
            // Increase size of thread pool until it can cope with the workload
        }
        return poolSize;
    }

    private final int threadLoad;

    public ParallelSimulationManager(Node root, StochasticVariableRegistry registry, int runs, int threadLoad) {
        super(root, registry, runs);
        this.threadLoad = threadLoad;
    }

    @Override
    public void run() {
        // Start a simulation only if one has not been started yet
        if (progressLatch == null) {
            int runCount = runs.getValue() - runs.getKey() + 1;
            int treeSize = TreeUtilities.getTreeSize(root);
            int totalWorkload = runCount * treeSize;

            int poolSize = calculateThreadPoolSize(totalWorkload, threadLoad);
            progressLatch = new CountDownLatch(poolSize);

            CompositeCompletionListener compositeCompletionListener
                    = new CompositeCompletionListener();
            compositeCompletionListener
                    .putCompletionListener(
                            new LatchLoweringCompletionListener(progressLatch)
                    );
            if (completionListener != null) {
                compositeCompletionListener
                        .putCompletionListener(completionListener);
            }

            log.info(String.format("Size:%d\tRuns:%d\tThreads:%d", treeSize, runCount, poolSize));
            for (Pair<Integer, Integer> simulationRange
                    : calculateSimulationRanges(runCount, poolSize)) {
                SingleThreadSimulationManager worker = new SingleThreadSimulationManager(
                        root, nodeValueRegistry, context, simulationRange);
                worker.setCompletionListener(compositeCompletionListener);
                Thread thread = new Thread(worker);
                thread.start();
            }
        }
    }

}
