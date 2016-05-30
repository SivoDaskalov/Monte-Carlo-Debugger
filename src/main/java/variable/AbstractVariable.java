/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable;

import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractVariable implements StochasticVariable {

    protected static final Logger log = LoggerFactory.getLogger(AbstractVariable.class);

    @XmlAttribute
    protected String id;
    protected Distribution distribution;
    @XmlAttribute
    protected Double from;
    @XmlAttribute
    protected Double to;
    protected final Random random;

    public AbstractVariable() {
        random = new Random();
    }

    public AbstractVariable(String id, Distribution distribution, Double from, Double to, Random random) {
        this.id = id;
        this.distribution = distribution;
        this.from = from;
        this.to = to;
        this.random = random;
    }

    public String getId() {
        return id;
    }

    protected void log(double value) {
//        log.debug(distribution + "\t" + id + "\t" + value);
    }

}
