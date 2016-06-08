/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.binary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import node.impl.AbstractNode;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "division")
public class DivisionNode extends AbstractBinaryNode {

    public DivisionNode() {
        super();
    }

    public DivisionNode(AbstractNode dividend, AbstractNode divisor) {
        super();
        arguments[0] = dividend;
        arguments[0].setRole("dividend");
        arguments[1] = divisor;
        arguments[1].setRole("divisor");
    }

    public Node getDividend() {
        return arguments[0];
    }

    public void setDividend(Node dividend) {
        arguments[0] = dividend;
        arguments[0].setRole("dividend");
    }

    public Node getDivisor() {
        return arguments[1];
    }

    public void setDivisor(Node divisor) {
        arguments[1] = divisor;
        arguments[1].setRole("divisor");
    }

    @Override
    protected double calculate(SimulationContext context) {
        Double value = getDividend().getValue(context) / getDivisor().getValue(context);
        if (value.isInfinite()) {
            value = Double.NaN;
        }
        return value;
    }

}
