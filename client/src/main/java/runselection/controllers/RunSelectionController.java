/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package runselection.controllers;

import debugging.views.DebuggingView;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.DebugContext;

/**
 *
 * @author sdaskalov
 */
public class RunSelectionController implements ListSelectionListener {

    private final DebugContext context;
    private final DebuggingView panel;

    public RunSelectionController(DebugContext context, DebuggingView panel) {
        this.context = context;
        this.panel = panel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        context.setCurrentRun(list.getSelectedIndex() + 1);
        context.resetDebugging();
        if (panel != null) {
            panel.updateTitle(context.getCurrentRun());
            panel.reset();
        }
    }

}
