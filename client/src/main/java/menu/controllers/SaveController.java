/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package menu.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import jaxb.JaxbUtils;
import view.SimulationFrame;

/**
 *
 * @author sdaskalov
 */
public class SaveController implements ActionListener {

    private final SimulationFrame frame;
    private final JFileChooser fileChooser;

    public SaveController(SimulationFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser("src/main/resources/");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.getDebugContext().getCurrentSimulation() != null) {
            if (fileChooser.showDialog(frame, "Select file to save the current simulation")
                    == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JaxbUtils
                        .getInstance()
                        .marshal(
                                frame.getDebugContext().getCurrentSimulation(),
                                file.getPath());
            }
        }
    }

}
