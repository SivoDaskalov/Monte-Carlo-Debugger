/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import debugging.views.DebuggingView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import menu.views.MenuView;
import model.DebugContext;
import runselection.views.RunSelectionView;
import statistics.views.StatisticsView;

/**
 *
 * @author sdaskalov
 */
public class SimulationFrame extends JFrame {

    private final DebugContext context;
    private final MenuView menuView;
    private final RunSelectionView runSelectionView;
    private final DebuggingView debuggingView;
    private final StatisticsView statisticsView;

    public SimulationFrame(String title, DebugContext context,
            MenuView menuView,
            RunSelectionView runSelectionView,
            DebuggingView debuggingView,
            StatisticsView statisticsView) {

        this.menuView = menuView;
        this.runSelectionView = runSelectionView;
        this.debuggingView = debuggingView;
        this.statisticsView = statisticsView;

        this.setLayout(new BorderLayout(20, 20));
        this.add(menuView, BorderLayout.NORTH);
        this.add(runSelectionView, BorderLayout.WEST);
        this.add(debuggingView, BorderLayout.CENTER);
        this.add(statisticsView, BorderLayout.EAST);

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

    public MenuView getMenuView() {
        return menuView;
    }

    public RunSelectionView getRunSelectionView() {
        return runSelectionView;
    }

    public DebuggingView getDebuggingView() {
        return debuggingView;
    }

    public StatisticsView getStatisticsView() {
        return statisticsView;
    }

    public DebugContext getDebugContext() {
        return context;
    }

}
