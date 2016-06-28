/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package debugging.controllers;

import debugging.views.DebuggingView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class StepIntoDebugController extends AbstractDebugController {

    public StepIntoDebugController(DebugContext context, DebuggingView panel) {
        super(context, panel);
        hook();
    }

    @Override
    final void hook() {
        panel.setStepIntoButtonListener(this);
    }

    @Override
    public DefaultMutableTreeNode handle() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) context
                .getCurrentlyDebuggedNode();
        if (node != null && node.getChildCount() > 0) {
            panel.getTree().expandPath(new TreePath(node.getPath()));
            context.setCurrentlyDebuggedNode(
                    (DefaultMutableTreeNode) node.getFirstChild());
        }
        return node;
    }

}
