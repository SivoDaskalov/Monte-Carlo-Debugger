/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "product")
public class ProductNode extends AbstractGroupNode {

    public ProductNode() {
        super();
    }

    public ProductNode(List<Node> elements) {
        super(elements);
    }

    @Override
    protected double calculate(SimulationContext context) {
        double product = 1;
        for (Node element : elements) {
            product *= element.getValue(context);
        }
        return product;
    }

}
