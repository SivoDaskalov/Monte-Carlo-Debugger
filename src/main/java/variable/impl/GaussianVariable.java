/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.impl;

import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import variable.AbstractVariable;
import variable.Distribution;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public class GaussianVariable extends AbstractVariable {

    // Default standard deviation set to a third of the mean
    private static Double defaultStandardDeviation = 1.0 / 3.0;

    private Double mean;
    @XmlAttribute
    private Double deviation;

    public GaussianVariable() {
    }

    public GaussianVariable(String id, Double from, Double to, Random random) {
        this(id, from, to, random, ((to - from) / 2) * defaultStandardDeviation);
    }

    public GaussianVariable(String id, Double from, Double to, Random random, Double deviation) {
        super(id, Distribution.GAUSSIAN, from, to, random);
        this.mean = (from + to) / 2;
        this.deviation = deviation;
    }

    @Override
    public Double sample() {
        Double result;
        do {
            result = random.nextGaussian() * deviation + mean;
        } while (result < from || result > to);
        log(result);
        return result;
    }
}
