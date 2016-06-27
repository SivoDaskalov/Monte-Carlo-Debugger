/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package debugging.controllers;

import debugging.views.DebuggingView;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import model.DebugContext;
import model.DebuggedNode;

/**
 *
 * @author sdaskalov
 */
public abstract class AbstractDebugController implements ActionListener {

    protected final DebugContext context;
    protected final DebuggingView panel;

    public AbstractDebugController(DebugContext context, DebuggingView panel) {
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
        TreePath selectionPath = tree.getSelectionPath();
        ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(changedBranch);
        tree.setSelectionPath(selectionPath);
        for (int i = 0; i < expandedRows.size(); i++) {
            tree.expandRow(expandedRows.get(i));
        }
        if (context.getCurrentlyDebuggedNode() != null) {
            tree.scrollPathToVisible(new TreePath(context.getCurrentlyDebuggedNode().getPath()));
        }
    }
}
