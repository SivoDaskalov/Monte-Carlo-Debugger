/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class SimulationFrame extends JFrame {

    private final DebugContext context;

    public SimulationFrame(String title, DebugContext context, 
            MainMenuPanel mainMenu,
            RunSelectorPanel runSelectorPanel,
            DebugTreePanel debugTreePanel,
            NodeStatisticsPanel nodeStatisticsPanel) {
        this.context = context;
        this.setTitle(title);
        this.setLayout(new BorderLayout(20, 20));
        this.add(mainMenu, BorderLayout.NORTH);
        this.add(runSelectorPanel, BorderLayout.WEST);
        this.add(debugTreePanel, BorderLayout.CENTER);
        this.add(nodeStatisticsPanel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.repaint();
        this.setResizable(false);
    }

    public DebugContext getContext() {
        return context;
    }

}
