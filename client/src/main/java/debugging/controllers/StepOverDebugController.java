/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package debugging.controllers;

import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import debugging.views.DebuggingView;

/**
 *
 * @author sdaskalov
 */
public class StepOverDebugController extends AbstractDebugController {

    public StepOverDebugController(DebugContext context, DebuggingView panel) {
        super(context, panel);
        hook();
    }

    @Override
    final void hook() {
        panel.setStepOverButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode node = context.getCurrentlyDebuggedNode();
        if (node != null) {
            debugChildren(node);
            DefaultMutableTreeNode next = node.getNextSibling();
            if (next != null) {
                context.setCurrentlyDebuggedNode(next);
            } else {
                context.setCurrentlyDebuggedNode((DefaultMutableTreeNode) node.getParent());
            }
        }
        after(node);
    }

}
