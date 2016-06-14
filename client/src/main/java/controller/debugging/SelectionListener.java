/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import tree.DebuggedNode;
import view.NodeStatisticsPanel;

/**
 *
 * @author sdaskalov
 */
public class SelectionListener implements TreeSelectionListener {

    private final DebugContext context;
    private final NodeStatisticsPanel panel;

    public SelectionListener(DebugContext context) {
        this.context = context;
        this.panel = null;
    }

    public SelectionListener(DebugContext context, NodeStatisticsPanel panel) {
        this.context = context;
        this.panel = panel;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        JTree tree = (JTree) e.getSource();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node != null) {
            DebuggedNode debuggedNode = (DebuggedNode) node.getUserObject();
            if (debuggedNode != null) {
                String selectedNodeId = debuggedNode.getNode().getId();
                context.setSelectedNode(selectedNodeId);
                if (panel != null) {
                    panel.updateFields();
                    panel.repaint();
                }
            }
        }
    }

}
