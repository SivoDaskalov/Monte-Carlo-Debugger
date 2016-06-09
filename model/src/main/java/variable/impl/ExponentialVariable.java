/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.impl;

import javax.xml.bind.annotation.XmlRootElement;
import variable.AbstractVariable;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "exponential")
public class ExponentialVariable extends AbstractVariable {

    private static final double defaultLambda = 1.0;

    private double lambda;

    public ExponentialVariable() {
        super();
        lambda = defaultLambda;
    }

    public ExponentialVariable(String id) {
        super(id);
        lambda = defaultLambda;
    }

    public ExponentialVariable(String id, long seed) {
        this(id, seed, defaultLambda);
    }

    public ExponentialVariable(String id, long seed, double lambda) {
        super(id, seed);
        this.lambda = lambda;
    }

    public Double getLambda() {
        return lambda;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    @Override
    public double sample() {
        return Math.log(1 - random.nextDouble()) / (-lambda);
    }

}
