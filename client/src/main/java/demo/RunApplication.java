/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package demo;

import model.DebugContext;
import view.ViewFactory;

/**
 *
 * @author sdaskalov
 */
public class RunApplication {

    public static void main(String argv[]) {
        ViewFactory.getInstance().makeSimulationFrame("Monte Carlo simulation debugger", new DebugContext());
    }

}
