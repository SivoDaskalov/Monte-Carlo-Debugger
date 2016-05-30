/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractVariable implements StochasticVariable {

    protected static final Logger log = LoggerFactory.getLogger(AbstractVariable.class);
    protected static final AtomicLong idGenerator = new AtomicLong();

    protected String id;
    protected RandomGenerator random;

    public AbstractVariable() {
        this.id = "" + idGenerator.getAndIncrement();
        this.random = new JDKRandomGenerator();
        random.setSeed(System.currentTimeMillis());
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
