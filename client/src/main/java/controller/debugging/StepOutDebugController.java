/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import view.tree.DebugTreePanel;

/**
 *
 * @author sdaskalov
 */
public class StepOutDebugController extends AbstractDebugController implements ActionListener {

    public StepOutDebugController(DebugContext context, DebugTreePanel panel) {
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
            context.setCurrentlyDebuggedNode((DefaultMutableTreeNode) node.getParent());
            debugChildren(node);
            do {
                debugChildren(node);
                node = node.getNextSibling();
            } while (node != null);
        }
        panel.repaint();
    }

}
