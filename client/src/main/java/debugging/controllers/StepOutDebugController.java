/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package debugging.controllers;

import debugging.views.DebuggingView;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class StepOutDebugController extends AbstractDebugController {

    public StepOutDebugController(DebugContext context, DebuggingView panel) {
        super(context, panel);
        hook();
    }

    @Override
    final void hook() {
        panel.setStepOutButtonListener(this);
    }

    @Override
    public DefaultMutableTreeNode handle() {
        DefaultMutableTreeNode node = context.getCurrentlyDebuggedNode();
        if (node != null) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
            context.setCurrentlyDebuggedNode((DefaultMutableTreeNode) node.getParent());
            if (parent == null) {
                parent = node;
            }
            do {
                debugChildren(node);
                node = node.getNextSibling();
            } while (node != null);
            return (DefaultMutableTreeNode) parent;
        }
        return null;
    }

}
