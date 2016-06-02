/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.impl;

import javax.xml.bind.annotation.XmlElement;
import variable.AbstractVariable;

/**
 *
 * @author sdaskalov
 */
public class ExponentialVariable extends AbstractVariable {

    private static final double defaultLambda = 1.0;

    @XmlElement(name = "lambda")
    private final Double lambda;

    public ExponentialVariable() {
        super();
        this.lambda = defaultLambda;
    }

    public ExponentialVariable(String id, long seed) {
        this(id, seed, defaultLambda);
    }

    public ExponentialVariable(String id, long seed, double lambda) {
        super(id, seed);
        this.lambda = lambda;
    }

    @Override
    public double sample() {
        return Math.log(1 - random.nextDouble()) / (-lambda);
    }

}
