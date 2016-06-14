/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class SimulationFrame extends JFrame {

    private static final Dimension preferredSize = new Dimension(1200, 700);
    private final DebugContext context;

    public SimulationFrame(String title, DebugContext context,
            RunSelectorPanel runSelectorPanel,
            DebugTreePanel debugTreePanel,
            NodeStatisticsPanel nodeStatisticsPanel) {
        this.context = context;
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.add(runSelectorPanel, BorderLayout.WEST);
        this.add(debugTreePanel, BorderLayout.CENTER);
        this.add(nodeStatisticsPanel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setPreferredSize(preferredSize);
        this.pack();
        this.setVisible(true);
        this.repaint();
        this.setResizable(false);
    }

    public DebugContext getContext() {
        return context;
    }

}
