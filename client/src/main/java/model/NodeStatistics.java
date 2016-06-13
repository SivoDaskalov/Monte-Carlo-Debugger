/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package model;

/**
 *
 * @author sdaskalov
 */
public class NodeStatistics {

    private final String id;
    private final double[] values;
    private final double average;
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
        average = sum / values.length;
    }

    public String getId() {
        return id;
    }

    public double getAverage() {
        return average;
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
