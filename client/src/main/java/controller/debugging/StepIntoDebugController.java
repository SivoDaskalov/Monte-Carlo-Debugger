/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.DebugContext;
import view.tree.DebugTreePanel;

/**
 *
 * @author sdaskalov
 */
public class StepIntoDebugController extends AbstractDebugController implements ActionListener {

    public StepIntoDebugController(DebugContext context, DebugTreePanel panel) {
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
