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
    public DefaultMutableTreeNode handle() {
        context.resetDebugging();
        return context.getRoot();
    }

}
