/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import debugging.controllers.NodeSelectionController;
import debugging.controllers.ResetDebugController;
import debugging.controllers.StepIntoDebugController;
import debugging.controllers.StepOutDebugController;
import debugging.controllers.StepOverDebugController;
import debugging.views.DebuggingView;
import menu.controllers.OpenController;
import menu.controllers.SaveController;
import menu.controllers.SimulateController;
import menu.views.MenuView;
import model.DebugContext;
import runselection.controllers.RunSelectionController;
import runselection.views.RunSelectionView;
import statistics.views.StatisticsView;

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

    public RunSelectionView makeRunSelectionView(DebugContext context, DebuggingView debuggingView) {
        RunSelectionView panel = new RunSelectionView(context);
        panel.setListSelectionListener(new RunSelectionController(context, debuggingView));
        return panel;
    }

    public DebuggingView makeDebuggingView(DebugContext context, StatisticsView statisticsPanel) {
        DebuggingView panel = new DebuggingView(context);
        panel.setTreeSelectionListener(new NodeSelectionController(context, statisticsPanel));
        panel.setResetButtonListener(new ResetDebugController(context, panel));
        panel.setStepIntoButtonListener(new StepIntoDebugController(context, panel));
        panel.setStepOutButtonListener(new StepOutDebugController(context, panel));
        panel.setStepOverButtonListener(new StepOverDebugController(context, panel));
        return panel;
    }

    public StatisticsView makeStatisticsView(DebugContext context) {
        return new StatisticsView(context);
    }

    public MenuView makeMenuView(DebugContext context) {
        MenuView panel = new MenuView();
        return panel;
    }

    public SimulationFrame makeSimulationFrame(String title, DebugContext context) {
        MenuView mainMenu = makeMenuView(context);
        StatisticsView nodeStatisticsPanel = makeStatisticsView(context);
        DebuggingView debugTreePanel = makeDebuggingView(context, nodeStatisticsPanel);
        RunSelectionView runSelectorPanel = makeRunSelectionView(context, debugTreePanel);
        SimulationFrame simulationFrame = new SimulationFrame(
                title, context, mainMenu, runSelectorPanel, debugTreePanel, nodeStatisticsPanel);
        mainMenu.setOpenButtonListener(new OpenController(simulationFrame));
        mainMenu.setSimulateButtonListener(new SimulateController(simulationFrame));
        mainMenu.setSaveButtonListener(new SaveController(simulationFrame));
        return simulationFrame;
    }

}
