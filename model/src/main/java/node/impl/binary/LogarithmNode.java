/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
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
@XmlRootElement(name = "logarithm")
public class LogarithmNode extends AbstractBinaryNode {

    public LogarithmNode() {
        super();
    }

    public LogarithmNode(AbstractNode argument, AbstractNode base) {
        super();
        arguments[0] = argument;
        arguments[0].setRole("argument");
        arguments[1] = base;
        arguments[1].setRole("base");
    }

    public Node getArgument() {
        return arguments[0];
    }

    public void setArgument(Node argument) {
        arguments[0] = argument;
        arguments[0].setRole("argument");
    }

    public Node getBase() {
        return arguments[1];
    }

    public void setBase(Node base) {
        arguments[1] = base;
        arguments[1].setRole("base");
    }

    @Override
    protected double calculate(SimulationContext context) {
        return Math.log(getArgument().getValue(context)) / Math.log(getBase().getValue(context));
    }

}
