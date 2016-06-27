/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package variable.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.math3.distribution.GammaDistribution;
import variable.AbstractVariable;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "gamma")
public class GammaVariable extends AbstractVariable {

    private static final double defaultScale = 1.0;
    private static final double defaultShape = 1.0;

    private double scale;
    private double shape;
    @XmlTransient
    private GammaDistribution distribution;

    public GammaVariable() {
        super();
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    public GammaVariable(String id) {
        super(id);
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    public GammaVariable(String id, long seed) {
        super(id, seed);
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    private void updateDistribution() {
        distribution = new GammaDistribution(random, scale, shape,
                GammaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        updateDistribution();
    }

    public double getShape() {
        return shape;
    }

    public void setShape(double shape) {
        this.shape = shape;
        updateDistribution();
    }

    @Override
    public double sample() {
        return distribution.sample();
    }

}
