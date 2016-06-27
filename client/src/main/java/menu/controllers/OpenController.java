/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package menu.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import jaxb.JaxbUtils;
import model.DebugContext;
import simulation.SimulationResponse;
import view.SimulationFrame;

/**
 *
 * @author sdaskalov
 */
public class OpenController implements ActionListener {

    private final SimulationFrame frame;
    private final JFileChooser fileChooser;

    public OpenController(SimulationFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser("src/main/resources/");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fileChooser.showDialog(frame, "Select simulation") == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            SimulationResponse unboxed
                    = JaxbUtils
                    .getInstance()
                    .unmarshal(file.getPath());
            if (unboxed != null) {
                frame.getMenuView().setFilename(file.getPath());
                DebugContext context = frame.getDebugContext();
                context.setup(unboxed);
                frame.getRunSelectionView().updateRunList();
                frame.getDebuggingView().setTreeModel(new DefaultTreeModel(context.getRoot()));
                frame.getStatisticsView().update();
            } else {
                JOptionPane.showMessageDialog(frame, "Selected file is not a simulation");
            }
        }
    }
}
