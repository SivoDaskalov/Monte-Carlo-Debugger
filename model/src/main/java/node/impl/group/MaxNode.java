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
@XmlRootElement(name = "max")
public class MaxNode extends AbstractGroupNode {

    public MaxNode() {
        super();
    }

    public MaxNode(List<Node> children) {
        super(children);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double max = Double.MIN_VALUE;
        for (Node child : children) {
            double current = child.getValue(context);
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

}
