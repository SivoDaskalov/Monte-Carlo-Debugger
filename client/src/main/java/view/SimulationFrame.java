/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import menu.views.MenuView;
import runselection.views.RunSelectionView;
import statistics.views.StatisticsView;
import debugging.views.DebuggingView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class SimulationFrame extends JFrame {

    private final DebugContext context;
    private final MenuView mainMenuPanel;
    private final RunSelectionView runSelectorPanel;
    private final DebuggingView debugTreePanel;
    private final StatisticsView nodeStatisticsPanel;

    public SimulationFrame(String title, DebugContext context,
            MenuView mainMenuPanel,
            RunSelectionView runSelectorPanel,
            DebuggingView debugTreePanel,
            StatisticsView nodeStatisticsPanel) {

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
        
        // Center frame on screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

    public MenuView getMainMenuPanel() {
        return mainMenuPanel;
    }

    public RunSelectionView getRunSelectorPanel() {
        return runSelectorPanel;
    }

    public DebuggingView getDebugTreePanel() {
        return debugTreePanel;
    }

    public StatisticsView getNodeStatisticsPanel() {
        return nodeStatisticsPanel;
    }

    public DebugContext getContext() {
        return context;
    }

}
