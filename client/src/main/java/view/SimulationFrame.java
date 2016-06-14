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
    private final MainMenuPanel mainMenuPanel;
    private final RunSelectorPanel runSelectorPanel;
    private final DebugTreePanel debugTreePanel;
    private final NodeStatisticsPanel nodeStatisticsPanel;

    public SimulationFrame(String title, DebugContext context,
            MainMenuPanel mainMenuPanel,
            RunSelectorPanel runSelectorPanel,
            DebugTreePanel debugTreePanel,
            NodeStatisticsPanel nodeStatisticsPanel) {

        this.mainMenuPanel = mainMenuPanel;
        this.runSelectorPanel = runSelectorPanel;
        this.debugTreePanel = debugTreePanel;
        this.nodeStatisticsPanel = nodeStatisticsPanel;

        this.setLayout(new BorderLayout(20, 20));
        this.add(mainMenuPanel, BorderLayout.NORTH);
        this.add(runSelectorPanel, BorderLayout.WEST);
        this.add(debugTreePanel, BorderLayout.CENTER);
        this.add(nodeStatisticsPanel, BorderLayout.EAST);

        this.context = context;
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.repaint();
        this.setResizable(false);
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public RunSelectorPanel getRunSelectorPanel() {
        return runSelectorPanel;
    }

    public DebugTreePanel getDebugTreePanel() {
        return debugTreePanel;
    }

    public NodeStatisticsPanel getNodeStatisticsPanel() {
        return nodeStatisticsPanel;
    }

    public DebugContext getContext() {
        return context;
    }

}
