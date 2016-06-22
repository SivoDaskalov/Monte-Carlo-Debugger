/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package menu.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultTreeModel;
import model.DebugContext;
import simulation.SimulationResponse;
import simulation.service.client.SimulationServiceClient;
import view.SimulationFrame;

/**
 *
 * @author sdaskalov
 */
public class SimulateController implements ActionListener {

    private final SimulationServiceClient service;
    private final SimulationFrame frame;

    public SimulateController(SimulationFrame frame) {
        this.frame = frame;
        service = new SimulationServiceClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!service.isConnected()){
            service.reconnect();
        }
        SimulationResponse currentSimulation = frame.getDebugContext().getCurrentSimulation();
        if (currentSimulation != null && service.isConnected()) {
            SimulationResponse response = service.simulate(currentSimulation);
            frame.getMenuView().setFilename(response.getProperties().getTitle());
            DebugContext context = frame.getDebugContext();
            context.setup(response);
            frame.getRunSelectionView().updateRunList();
            frame.getDebuggingView().setTreeModel(new DefaultTreeModel(context.getRoot()));
            frame.getStatisticsView().update();
        }
    }

}
