/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package debugging.controllers;

import debugging.views.DebuggingView;
import java.awt.event.ActionEvent;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class ResetDebugController extends AbstractDebugController {

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
