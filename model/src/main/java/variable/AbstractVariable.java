/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package variable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import variable.impl.ExponentialVariable;
import variable.impl.GammaVariable;
import variable.impl.GaussianVariable;
import variable.impl.LogNormalVariable;
import variable.impl.UniformVariable;

/**
 *
 * @author sdaskalov
 */
@XmlSeeAlso({ExponentialVariable.class, GammaVariable.class,
    GaussianVariable.class, LogNormalVariable.class, UniformVariable.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractVariable implements StochasticVariable {

    @XmlTransient
    protected static final Logger log = LoggerFactory.getLogger(AbstractVariable.class);
    @XmlTransient
    protected static final AtomicLong idGenerator = new AtomicLong();
    @XmlTransient
    protected RandomGenerator random;
    @XmlAttribute
    protected String id;

    public AbstractVariable() {
        this("" + idGenerator.getAndIncrement());
    }

    public AbstractVariable(String id) {
        this(id, System.currentTimeMillis());
    }

    public AbstractVariable(String id, long seed) {
        this.id = id;
        this.random = new JDKRandomGenerator();
        random.setSeed(seed);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @Override
    public double[] sample(int sampleSize) {
        double[] samples = new double[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            samples[i] = sample();
        }
        return samples;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractVariable other = (AbstractVariable) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
