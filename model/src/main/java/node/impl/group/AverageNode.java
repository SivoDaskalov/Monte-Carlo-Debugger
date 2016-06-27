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
@XmlRootElement(name = "average")
public class AverageNode extends AbstractGroupNode {

    public AverageNode() {
        super();
    }

    public AverageNode(List<Node> children) {
        super(children);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double sum = 0;
        for (Node child : children) {
            sum += child.getValue(context);
        }
        return sum / children.size();
    }

}
