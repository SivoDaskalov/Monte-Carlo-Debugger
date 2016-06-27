/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package node.impl.group;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "min")
public class MinNode extends AbstractGroupNode {

    public MinNode() {
        super();
    }

    public MinNode(List<Node> children) {
        super(children);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double min = Double.MAX_VALUE;
        for (Node child : children) {
            double current = child.getValue(context);
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

}
