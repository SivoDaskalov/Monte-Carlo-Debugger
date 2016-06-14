/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import controller.debugging.ResetDebugController;
import controller.debugging.SelectionListener;
import controller.debugging.StepIntoDebugController;
import controller.debugging.StepOutDebugController;
import controller.debugging.StepOverDebugController;
import model.DebugContext;
import view.tree.DebugTreePanel;

/**
 *
 * @author sdaskalov
 */
public class ViewFactory {

    private static final ViewFactory instance = new ViewFactory();

    private ViewFactory() {
    }

    public static ViewFactory getInstance() {
        return instance;
    }

    public DebugTreePanel makeDebugTreePanel(DebugContext context) {
        DebugTreePanel panel = new DebugTreePanel(context);
        panel.setTreeSelectionListener(new SelectionListener(context));
        panel.setResetButtonListener(new ResetDebugController(context, panel));
        panel.setStepIntoButtonListener(new StepIntoDebugController(context, panel));
        panel.setStepOutButtonListener(new StepOutDebugController(context, panel));
        panel.setStepOverButtonListener(new StepOverDebugController(context, panel));
        return panel;
    }

}
