/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package debugging.controllers;

import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.DebugContext;
import debugging.views.DebuggingView;

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
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) context
                .getCurrentlyDebuggedNode();
        if (node != null && node.getChildCount() > 0) {
            panel.getTree().expandPath(new TreePath(node.getPath()));
            context.setCurrentlyDebuggedNode(
                    (DefaultMutableTreeNode) node.getFirstChild());
        }
        after(node);
    }

}
