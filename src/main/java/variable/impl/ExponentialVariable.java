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
public class ExponentialVariable extends AbstractVariable {

    public ExponentialVariable() {
    }

    public ExponentialVariable(String id, Double from, Double to, Random random) {
        super(id, Distribution.EXPONENTIAL, from, to, random);
    }

    @Override
    public Double sample() {
        return null;
    }

}
