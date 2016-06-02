/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import node.impl.AbstractNode;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "division")
public class DivisionNode extends AbstractNode {

    private Node dividend;
    private Node divisor;

    public DivisionNode() {
    }

    public DivisionNode(Node dividend, Node divisor) {
        super();
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public Node getDividend() {
        return dividend;
    }

    public void setDividend(Node dividend) {
        this.dividend = dividend;
    }

    public Node getDivisor() {
        return divisor;
    }

    public void setDivisor(Node divisor) {
        this.divisor = divisor;
    }

    @Override
    protected double calculate(SimulationContext context) {
        Double value = dividend.getValue(context) / divisor.getValue(context);
        if (value.isInfinite()) {
            value = Double.NaN;
        }
        return value;
    }

}
