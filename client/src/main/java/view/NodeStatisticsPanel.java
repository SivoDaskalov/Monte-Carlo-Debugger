/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.DebugContext;
import model.NodeStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class NodeStatisticsPanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(400, 600);
    private static final Dimension preferredFieldSize = new Dimension(100, 30);
    private static final Dimension preferredChartSize = new Dimension(400, 220);

    private final DebugContext context;
    private final JTextField maxField;
    private final JTextField minField;
    private final JTextField meanField;

    private final XYSeries series;
    private final JFreeChart valueChart;
    private final JPanel histogramPanel;

    public NodeStatisticsPanel(DebugContext context) {
        this.context = context;
        this.setLayout(new BorderLayout(30, 30));
        this.setBackground(Styles.defaultPanelBackgroundColor);
        this.setPreferredSize(preferredSize);
        this.setBorder(Styles.padding);

        minField = new JTextField();
        maxField = new JTextField();
        meanField = new JTextField();

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Styles.defaultPanelBackgroundColor);
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.add(setupFieldPanel(minField, "Min"));
        fieldPanel.add(Box.createHorizontalStrut(Styles.strut));
        fieldPanel.add(setupFieldPanel(maxField, "Max"));
        fieldPanel.add(Box.createHorizontalStrut(Styles.strut));
        fieldPanel.add(setupFieldPanel(meanField, "Mean"));
        this.add(packTitleAndFields(fieldPanel), BorderLayout.NORTH);

        this.add(Box.createVerticalStrut(Styles.strut));

        series = new XYSeries("Values");
        valueChart = ChartFactory.createXYLineChart("Values",
                null, null, new XYSeriesCollection(series),
                PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = (XYPlot) valueChart.getPlot();
        plot.setBackgroundPaint(Styles.chartBackgroundColor);
        plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(valueChart);
        chartPanel.setPreferredSize(preferredChartSize);
        this.add(chartPanel, BorderLayout.CENTER);

        histogramPanel = new JPanel();
        histogramPanel.setPreferredSize(preferredChartSize);
        histogramPanel.setBackground(Styles.defaultPanelBackgroundColor);
        this.add(histogramPanel, BorderLayout.SOUTH);
        update();
    }

    private JPanel packTitleAndFields(JPanel fieldPanel) {
        JPanel titleFieldPanel = new JPanel();
        titleFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titleFieldPanel.setPreferredSize(new Dimension(400, 100));
        titleFieldPanel.setBackground(Styles.defaultPanelBackgroundColor);

        JLabel title = new JLabel("Selected node statistics");
        title.setFont(Styles.titleFont);
        this.add(title, BorderLayout.NORTH);

        titleFieldPanel.add(title);
        titleFieldPanel.add(Box.createVerticalStrut(Styles.strut));
        titleFieldPanel.add(fieldPanel);
        return titleFieldPanel;
    }

    private JPanel setupFieldPanel(JTextField field, String labelText) {
        JPanel panel = new JPanel();
        panel.setBackground(Styles.defaultPanelBackgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(labelText);
        label.setFont(Styles.labelFont);
        panel.add(label);

        field.setFont(Styles.labelFont);
        field.setEditable(false);
        field.setBackground(Styles.chartBackgroundColor);
        field.setPreferredSize(preferredFieldSize);
        panel.add(field);
        return panel;
    }

    public void update() {
        NodeStatistics statistics = context.getSelectedNodeStatistics();
        if (statistics == null) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        minField.setText(String.format(Styles.valueFormat, statistics.getMin()));
        maxField.setText(String.format(Styles.valueFormat, statistics.getMax()));
        meanField.setText(String.format(Styles.valueFormat, statistics.getMean()));
        updateNodeValues();
        recreateHistogramChartPanel();
    }

    private void updateNodeValues() {
        series.clear();
        NodeStatistics stats = context.getSelectedNodeStatistics();
        double[] values = stats.getValues();
        for (int i = 0; i < values.length; i++) {
            series.add(i + 1, values[i]);
        }
        updateRangeAxis();
    }

    private void updateRangeAxis() {
        XYPlot plot = (XYPlot) valueChart.getPlot();
        plot.getDomainAxis().setRange(0, context.getRunCount());
        Range dataRange = plot.getDataRange(plot.getRangeAxis());
        if (dataRange.getLowerBound() == dataRange.getUpperBound()) {
            plot.getRangeAxis().setRange(
                    dataRange.getLowerBound() - 1,
                    dataRange.getUpperBound() + 1);
        } else {
            plot.getRangeAxis().setRange(dataRange);
        }
    }

    private void recreateHistogramChartPanel() {
        histogramPanel.removeAll();
        NodeStatistics stats = context.getSelectedNodeStatistics();
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries(
                "Distribution",
                stats == null ? new double[1] : stats.getValues(),
                15);
        JFreeChart histogram = ChartFactory.createHistogram("Distribution",
                null, null, dataset, PlotOrientation.VERTICAL, false, false, false);
        histogram.getPlot().setBackgroundPaint(Styles.chartBackgroundColor);
        ChartPanel panel = new ChartPanel(histogram);
        panel.setPreferredSize(preferredChartSize);
        histogramPanel.add(panel);
        histogramPanel.revalidate();
    }
}
