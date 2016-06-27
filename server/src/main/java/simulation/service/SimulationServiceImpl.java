/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package simulation.service;

import javax.jws.WebService;
import node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import simulation.interfaces.SimulationManager;
import simulation.manager.ParallelSimulationManager;

/**
 *
 * @author sdaskalov
 */
@WebService(endpointInterface = "simulation.service.SimulationService")
public class SimulationServiceImpl implements SimulationService {

    private static final Logger logger = LoggerFactory.getLogger(SimulationServiceImpl.class);
    private static final int threadLoad = 100000 * 1000 / 4;

    @Override
    public SimulationResponse simulate(SimulationRequest request) {
        logger.info("Simulation request \"" + request.getProperties().getTitle() + "\" received");
        long start = System.currentTimeMillis();

        Node root = request.getFormula();
        StochasticVariableRegistry variables = request.getVariableRegistry();
        int runs = request.getProperties().getSimulationRuns();

        SimulationManager simulationManager = new ParallelSimulationManager(root, variables, runs, threadLoad);
        simulationManager.run();
        simulationManager.await();
        SimulationResponse response = new SimulationResponse(request, simulationManager);

        logger.info("Simulation of \"" + request.getProperties().getTitle()
                + "\" took " + (System.currentTimeMillis() - start) + " ms");
        return response;
    }
}
