/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import variable.impl.ExponentialVariable;
import variable.impl.GaussianVariable;
import variable.impl.UniformVariable;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class VariableSamplingTestCase {

    private static final Logger log = LoggerFactory.getLogger(VariableSamplingTestCase.class);
    private static final int sampleSize = 20;
    private static StochasticVariableRegistryImpl variables;

    public VariableSamplingTestCase() {
    }

    @Before
    public void setUp() {
        variables = new StochasticVariableRegistryImpl();
    }

    @Test
    public void gaussianSamplingTestCase() {
        variables.putVariable(new GaussianVariable("X", 1L));
        doSampling("Gaussian", "X");
    }

    @Test
    public void uniformSamplingTestCase() {
        variables.putVariable(new UniformVariable("Y", 1L));
        doSampling("Uniform", "Y");
    }

    @Test
    public void exponentialSamplingTestCase() {
        variables.putVariable(new ExponentialVariable("Z", 1L));
        doSampling("Exponential", "Z");
    }

    public void doSampling(String distribution, String id) {
        StochasticVariable variable = variables.getVariables().get(0);
        double[] samples = variable.sample(sampleSize);
        log.info(distribution + ": " + Arrays.toString(samples));
    }

}
