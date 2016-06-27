/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package node.impl.unary;

import javax.xml.bind.annotation.XmlRootElement;
import node.Node;
import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "absolute")
public class AbsoluteNode extends AbstractUnaryNode {

    public AbsoluteNode() {
    }

    public AbsoluteNode(Node argument) {
        super(argument);
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.abs(argument.getValue(context));
    }

}
