/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package variable.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.math3.distribution.LogNormalDistribution;
import variable.AbstractVariable;

/**
 *
 * @author sdaskalov
 */
@XmlRootElement(name = "logNormal")
public class LogNormalVariable extends AbstractVariable {

    private static final double defaultScale = 0.0;
    private static final double defaultShape = 1.0;

    private double scale;
    private double shape;
    @XmlTransient
    private LogNormalDistribution distribution;

    public LogNormalVariable() {
        super();
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    public LogNormalVariable(String id) {
        super(id);
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    public LogNormalVariable(String id, long seed) {
        super(id, seed);
        scale = defaultScale;
        shape = defaultShape;
        updateDistribution();
    }

    private void updateDistribution() {
        distribution = new LogNormalDistribution(random, scale, shape,
                LogNormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
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
