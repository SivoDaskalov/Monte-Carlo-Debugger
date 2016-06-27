/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package model;

/**
 *
 * @author sdaskalov
 */
public class NodeStatistics {

    private final String id;
    private final double[] values;
    private final double mean;
    private double min;
    private double max;

    public NodeStatistics(String id, double[] values) {
        this.id = id;
        this.values = values;
        min = values[0];
        max = values[0];
        double sum = 0;
        for (double value : values) {
            sum += value;
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        mean = sum / values.length;
    }

    public String getId() {
        return id;
    }

    public double getMean() {
        return mean;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double[] getValues() {
        return values;
    }

    public double getValue(int run) {
        return values[run];
    }
}
