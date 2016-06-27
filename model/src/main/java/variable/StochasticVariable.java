/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
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
