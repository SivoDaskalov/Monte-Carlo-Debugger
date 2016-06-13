/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
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
}
