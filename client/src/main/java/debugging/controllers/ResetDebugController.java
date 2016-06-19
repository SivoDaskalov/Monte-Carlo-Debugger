/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package debugging.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DebugContext;
import debugging.views.DebuggingView;

/**
 *
 * @author sdaskalov
 */
public class ResetDebugController extends AbstractDebugController implements ActionListener
{

    public ResetDebugController(DebugContext context, DebuggingView panel) {
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
        after(context.getRoot());
    }
    
}
