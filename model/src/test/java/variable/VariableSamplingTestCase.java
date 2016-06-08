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
import variable.impl.GammaVariable;
import variable.impl.GaussianVariable;
import variable.impl.LogNormalVariable;
import variable.impl.UniformVariable;
import simulation.StochasticVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class VariableSamplingTestCase {

    private static final Logger log = LoggerFactory.getLogger(VariableSamplingTestCase.class);
    private static final int sampleSize = 20;
    private static StochasticVariableRegistry variables;

    public VariableSamplingTestCase() {
    }

    @Before
    public void setUp() {
        variables = new StochasticVariableRegistry();
    }

    @Test
    public void gaussianSamplingTestCase() {
        variables.putVariable(new GaussianVariable("X0", 1L));
        doSampling("Gaussian", "X0");
    }

    @Test
    public void uniformSamplingTestCase() {
        variables.putVariable(new UniformVariable("X1", 1L));
        doSampling("Uniform", "X1");
    }

    @Test
    public void exponentialSamplingTestCase() {
        variables.putVariable(new ExponentialVariable("X2", 1L));
        doSampling("Exponential", "X2");
    }

    @Test
    public void logNormalSamplingTestCase() {
        variables.putVariable(new LogNormalVariable("X3", 1L));
        doSampling("LogNormal", "X3");
    }

    @Test
    public void gammaSamplingTestCase() {
        variables.putVariable(new GammaVariable("X4", 1L));
        doSampling("Gamma", "X4");
    }

    public void doSampling(String distribution, String id) {
        StochasticVariable variable = variables.getVariables().get(0);
        double[] samples = variable.sample(sampleSize);
        log.info(distribution + ": " + Arrays.toString(samples));
    }

}
