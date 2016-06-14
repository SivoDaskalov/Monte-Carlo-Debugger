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

/**
 *
 * @author sdaskalov
 */
public class SelectionListener implements TreeSelectionListener {

    private final DebugContext context;

    public SelectionListener(DebugContext context) {
        this.context = context;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        JTree tree = (JTree) e.getSource();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        String selectedNodeId = ((DebuggedNode) node.getUserObject()).getNode().getId();
        context.setSelectedNode(selectedNodeId);
    }

}
