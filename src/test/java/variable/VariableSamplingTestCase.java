/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import variable.impl.GaussianVariable;
import variable.impl.UniformVariable;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class VariableSamplingTestCase {

    private static StochasticVariableRegistryImpl variables;
    private static final int sampleCount = 10;

    public VariableSamplingTestCase() {
    }

    @Before
    public void setUp() {
        variables = new StochasticVariableRegistryImpl();
    }

    @Test
    public void gaussianSamplingTestCase() {
        variables.putVariable(new GaussianVariable("X", 0.0, 100.0,
                new Random(1L), 1.0));
        doSampling("X");
    }

    @Test
    public void uniformSamplingTestCase() {
        variables.putVariable(new UniformVariable("Y", 0.0, 100.0,
                new Random(1L)));
        doSampling("Y");
    }

    public void doSampling(String id) {
        for (int i = 0; i < sampleCount; i++) {
//            variables.sampleAll();
        }
    }
}
