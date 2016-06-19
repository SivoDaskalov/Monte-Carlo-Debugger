/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package debugging.controllers;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import model.DebuggedNode;
import statistics.views.StatisticsView;

/**
 *
 * @author sdaskalov
 */
public class NodeSelectionController implements TreeSelectionListener {

    private final DebugContext context;
    private final StatisticsView panel;

    public NodeSelectionController(DebugContext context) {
        this.context = context;
        this.panel = null;
    }

    public NodeSelectionController(DebugContext context, StatisticsView panel) {
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
                    panel.update();
                    panel.repaint();
                }
            }
        }
    }

}
