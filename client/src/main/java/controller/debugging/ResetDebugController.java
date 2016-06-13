/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package controller.debugging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DebugContext;
import view.tree.DebugTreePanel;

/**
 *
 * @author sdaskalov
 */
public class ResetDebugController extends AbstractDebugController implements ActionListener
{

    public ResetDebugController(DebugContext context, DebugTreePanel panel) {
        super(context, panel);
        hook();
    }

    @Override
    final void hook() {
        panel.setResetButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.resetDebugging();
        panel.repaint();
    }
    
}
