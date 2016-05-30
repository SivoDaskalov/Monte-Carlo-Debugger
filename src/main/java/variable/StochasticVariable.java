/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package variable;

/**
 *
 * @author sdaskalov
 */
public interface StochasticVariable {

    public String getId();

    public double sample();

    public double[] sample(int sampleSize);
}
