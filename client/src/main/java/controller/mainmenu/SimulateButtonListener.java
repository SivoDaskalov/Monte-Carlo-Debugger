/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.mainmenu;

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
public class SimulateButtonListener implements ActionListener {

    private final SimulationServiceClient service;
    private final SimulationFrame frame;

    public SimulateButtonListener(SimulationFrame frame) {
        this.frame = frame;
        service = new SimulationServiceClient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!service.isConnected()){
            service.reconnect();
        }
        SimulationResponse currentSimulation = frame.getContext().getCurrentSimulation();
        if (currentSimulation != null && service.isConnected()) {
            SimulationResponse response = service.simulate(currentSimulation);
            frame.getMainMenuPanel().setFilename(response.getProperties().getTitle());
            DebugContext context = frame.getContext();
            context.setup(response);
            frame.getRunSelectorPanel().updateRunList();
            frame.getDebugTreePanel().setTreeModel(new DefaultTreeModel(context.getRoot()));
            frame.getNodeStatisticsPanel().update();
        }
    }

}
