/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.factory;

import controller.debugging.ResetDebugController;
import controller.debugging.SelectionListener;
import controller.debugging.StepIntoDebugController;
import controller.debugging.StepOutDebugController;
import controller.debugging.StepOverDebugController;
import controller.mainmenu.OpenButtonListener;
import controller.runselector.RunSelectorListener;
import model.DebugContext;
import view.DebugTreePanel;
import view.MainMenuPanel;
import view.NodeStatisticsPanel;
import view.RunSelectorPanel;
import view.SimulationFrame;

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

    public RunSelectorPanel makeRunSelectorPanel(DebugContext context, DebugTreePanel treePanel) {
        RunSelectorPanel panel = new RunSelectorPanel(context);
        panel.setListSelectionListener(new RunSelectorListener(context, treePanel));
        return panel;
    }

    public DebugTreePanel makeDebugTreePanel(DebugContext context, NodeStatisticsPanel statisticsPanel) {
        DebugTreePanel panel = new DebugTreePanel(context);
        panel.setTreeSelectionListener(new SelectionListener(context, statisticsPanel));
        panel.setResetButtonListener(new ResetDebugController(context, panel));
        panel.setStepIntoButtonListener(new StepIntoDebugController(context, panel));
        panel.setStepOutButtonListener(new StepOutDebugController(context, panel));
        panel.setStepOverButtonListener(new StepOverDebugController(context, panel));
        return panel;
    }

    public NodeStatisticsPanel makeNodeStatisticsPanel(DebugContext context) {
        return new NodeStatisticsPanel(context);
    }

    public MainMenuPanel makeMainMenu(DebugContext context) {
        MainMenuPanel panel = new MainMenuPanel();
        return panel;
    }

    public SimulationFrame makeSimulationFrame(String title, DebugContext context) {
        MainMenuPanel mainMenu = makeMainMenu(context);
        NodeStatisticsPanel nodeStatisticsPanel = makeNodeStatisticsPanel(context);
        DebugTreePanel debugTreePanel = makeDebugTreePanel(context, nodeStatisticsPanel);
        RunSelectorPanel runSelectorPanel = makeRunSelectorPanel(context, debugTreePanel);
        SimulationFrame simulationFrame = new SimulationFrame(
                title, context, mainMenu, runSelectorPanel, debugTreePanel, nodeStatisticsPanel);
        mainMenu.setOpenButtonListener(new OpenButtonListener(simulationFrame));
        return simulationFrame;
    }

}
