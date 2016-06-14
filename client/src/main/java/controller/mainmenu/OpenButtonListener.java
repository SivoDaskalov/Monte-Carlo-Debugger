/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultTreeModel;
import jaxb.SimulationUnmarshaller;
import model.DebugContext;
import simulation.SimulationConfiguration;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import view.SimulationFrame;

/**
 *
 * @author sdaskalov
 */
public class OpenButtonListener implements ActionListener {

    private final SimulationFrame frame;
    private final JFileChooser fileChooser;

    public OpenButtonListener(SimulationFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser("src/main/resources/");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fileChooser.showDialog(frame, "Select simulation") == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            SimulationConfiguration unboxed
                    = SimulationUnmarshaller
                    .getInstance()
                    .unmarshal(file.getPath());
            if (unboxed != null) {
                frame.getMainMenuPanel().setFilename(file.getPath());
                DebugContext context = frame.getContext();
                if (SimulationResponse.class.isAssignableFrom(unboxed.getClass())) {
                    context.setup((SimulationResponse) unboxed);
                } else {
                    context.setup((SimulationRequest) unboxed);
                }
                frame.getRunSelectorPanel().updateRunList();
                frame.getDebugTreePanel().setTreeModel(new DefaultTreeModel(context.getRoot()));
                frame.getNodeStatisticsPanel().update();
            }
        }
    }
}
