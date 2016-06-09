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
@XmlRootElement(name = "gaussian")
public class GaussianVariable extends AbstractVariable {

    private static final double defaultMean = 0.5;
    // Default standard deviation set to a third of the mean
    private static final double defaultStandardDeviation = 1.0 / 3.0;

    private double mean;
    private double deviation;

    public GaussianVariable() {
        super();
        mean = defaultMean;
        deviation = defaultStandardDeviation;
    }

    public GaussianVariable(String id) {
        super(id);
        mean = defaultMean;
        deviation = defaultStandardDeviation;
    }

    public GaussianVariable(String id, long seed) {
        this(id, seed, defaultMean, defaultStandardDeviation);
    }

    public GaussianVariable(String id, long seed, double mean, double deviation) {
        super(id, seed);
        this.mean = mean;
        this.deviation = deviation;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }

    @Override
    public double sample() {
        return random.nextGaussian() * deviation + mean;
    }
}
