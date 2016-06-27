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
@XmlRootElement(name = "product")
public class ProductNode extends AbstractGroupNode {

    public ProductNode() {
        super();
    }

    public ProductNode(List<Node> children) {
        super(children);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double product = 1;
        for (Node child : children) {
            product *= child.getValue(context);
        }
        return product;
    }

}
