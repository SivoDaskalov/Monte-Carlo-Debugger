/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import variable.AbstractVariable;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public class GaussianVariable extends AbstractVariable {

    private static final double defaultMean = 0.5;
    // Default standard deviation set to a third of the mean
    private static final double defaultStandardDeviation = 1.0 / 3.0;

    private Double mean;
    private Double deviation;

    public GaussianVariable() {
        super();
        this.mean = defaultMean;
        this.deviation = defaultStandardDeviation;
    }

    public GaussianVariable(String id, long seed) {
        this(id, seed, defaultMean, defaultStandardDeviation);
    }

    public GaussianVariable(String id, long seed, double mean, double deviation) {
        super(id, seed);
        this.mean = mean;
        this.deviation = deviation;
    }

    @Override
    public double sample() {
        return random.nextGaussian() * deviation + mean;
    }
}
