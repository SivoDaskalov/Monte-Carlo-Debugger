/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package demo;

import model.DebugContext;
import view.factory.ViewFactory;

/**
 *
 * @author sdaskalov
 */
public class ApplicationDemo {

    public static void main(String argv[]) {
        ViewFactory.getInstance().makeSimulationFrame("Monte Carlo simulation debugger", new DebugContext());
    }

}
