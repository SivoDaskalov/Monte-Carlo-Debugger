/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package treeview;

import model.DebugContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationResponse;
import view.factory.ViewFactory;

/**
 *
 * @author sdaskalov
 */
public class TreeViewDemo {

    private static final Logger log = LoggerFactory.getLogger(TreeViewDemo.class);

    public TreeViewDemo(String url) {
        SimulationResponse response = SimulationResponseUnmarshaler.unmarshal(url);
        DebugContext context = new DebugContext(response);
        ViewFactory.getInstance().makeSimulationFrame("Simulation demo", context);
    }

    public static void main(String argv[]) {
        TreeViewDemo demo = new TreeViewDemo("src/main/resources/SimulationResponse.xml");
    }

}
