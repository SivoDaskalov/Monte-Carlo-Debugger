/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package treeview;

import jaxb.SimulationResponseUnmarshaler;
import model.DebugContext;
import simulation.SimulationResponse;
import view.factory.ViewFactory;

/**
 *
 * @author sdaskalov
 */
public class ApplicationDemo {

    public ApplicationDemo(String url) {
        SimulationResponse response = SimulationResponseUnmarshaler.unmarshal(url);
        DebugContext context = new DebugContext(response);
        ViewFactory.getInstance().makeSimulationFrame("Monte Carlo simulation debugger", context);
    }

    public static void main(String argv[]) {
        ApplicationDemo demo = new ApplicationDemo("src/main/resources/SimulationResponse.xml");
    }

}
