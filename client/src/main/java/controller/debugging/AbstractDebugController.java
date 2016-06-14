/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.DebugContext;
import tree.DebuggedNode;
import view.tree.DebugTreePanel;

/**
 *
 * @author sdaskalov
 */
public abstract class AbstractDebugController {

    protected final DebugContext context;
    protected final DebugTreePanel panel;

    public AbstractDebugController(DebugContext context, DebugTreePanel panel) {
        this.context = context;
        this.panel = panel;
    }

    protected void debugChildren(DefaultMutableTreeNode node) {
        ((DebuggedNode) (node.getUserObject())).setValueVisible(true);
        Enumeration children = node.children();
        while (children.hasMoreElements()) {
            debugChildren((DefaultMutableTreeNode) children.nextElement());
        }
    }

    abstract void hook();

    protected void after(DefaultMutableTreeNode changedBranch) {
        JTree tree = panel.getTree();
        List<Integer> expandedRows = new ArrayList<>();
        for (int i = 0; i < tree.getRowCount(); i++) {
            if (tree.isExpanded(i)) {
                expandedRows.add(i);
            }
        }
        ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(changedBranch);
        for (int i = 0; i < expandedRows.size(); i++) {
            tree.expandRow(expandedRows.get(i));
        }
    }
}
