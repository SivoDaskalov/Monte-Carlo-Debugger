/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable.impl;

import java.util.Random;
import variable.AbstractVariable;
import variable.Distribution;

/**
 *
 * @author sdaskalov
 */
public class UniformVariable extends AbstractVariable {

    public UniformVariable() {
    }

    public UniformVariable(String id, Double from, Double to, Random random) {
        super(id, Distribution.UNIFORM, from, to, random);
    }

    @Override
    public Double sample() {
        Double result = from + random.nextDouble() * (to - from);
        log(result);
        return result;
    }

}
