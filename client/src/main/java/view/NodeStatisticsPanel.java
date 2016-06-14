/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.DebugContext;
import model.NodeStatistics;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class NodeStatisticsPanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(new Dimension(150, 600));
    private static final Dimension preferredFieldSize = new Dimension(new Dimension(100, 30));
    private final DebugContext context;
    private final JTextField maxField;
    private final JTextField minField;
    private final JTextField avgField;

    public NodeStatisticsPanel(DebugContext context) {
        this.context = context;
        this.setBackground(Styles.selectedBackgroundColor);
        this.setPreferredSize(preferredSize);
        this.setBorder(Styles.padding);

        JPanel aggregatedPanel = new JPanel();
        aggregatedPanel.setBackground(Styles.selectedBackgroundColor);
        aggregatedPanel.setLayout(new BoxLayout(aggregatedPanel, BoxLayout.Y_AXIS));
        JLabel minLabel = new JLabel("Min");
        minLabel.setFont(Styles.labelFont);
        aggregatedPanel.add(minLabel);

        minField = new JTextField();
        minField.setFont(Styles.labelFont);
        minField.setEditable(false);
        minField.setPreferredSize(preferredFieldSize);
        aggregatedPanel.add(minField);

        aggregatedPanel.add(Box.createVerticalStrut(Styles.strut));

        JLabel maxLabel = new JLabel("Max");
        maxLabel.setFont(Styles.labelFont);
        aggregatedPanel.add(maxLabel);

        maxField = new JTextField();
        maxField.setFont(Styles.labelFont);
        maxField.setEditable(false);
        maxField.setPreferredSize(preferredFieldSize);
        aggregatedPanel.add(maxField);

        aggregatedPanel.add(Box.createVerticalStrut(Styles.strut));

        JLabel avgLabel = new JLabel("Average");
        avgLabel.setFont(Styles.labelFont);
        aggregatedPanel.add(avgLabel);

        avgField = new JTextField();
        avgField.setFont(Styles.labelFont);
        avgField.setEditable(false);
        avgField.setPreferredSize(preferredFieldSize);
        aggregatedPanel.add(avgField);
        this.add(aggregatedPanel);
    }

    public void updateFields() {
        NodeStatistics statistics = context.getSelectedNodeStatistics();
        minField.setText(String.format(Styles.valueFormat, statistics.getMin()));
        maxField.setText(String.format(Styles.valueFormat, statistics.getMax()));
        avgField.setText(String.format(Styles.valueFormat, statistics.getAverage()));
    }

}
