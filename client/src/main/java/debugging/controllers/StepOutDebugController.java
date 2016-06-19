/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package debugging.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import debugging.views.DebuggingView;

/**
 *
 * @author sdaskalov
 */
public class StepOutDebugController extends AbstractDebugController implements ActionListener {

    public StepOutDebugController(DebugContext context, DebuggingView panel) {
        super(context, panel);
        hook();
    }

    @Override
    final void hook() {
        panel.setStepOutButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            after((DefaultMutableTreeNode) parent);
        }
    }

}
