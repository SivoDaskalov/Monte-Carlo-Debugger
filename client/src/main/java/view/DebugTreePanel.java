/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import model.DebugContext;
import tree.JTreeUtils;
import view.renderers.NodeRendererResolver;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class DebugTreePanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(600, 600);
    private final JButton resetButton;
    private final JButton stepOverButton;
    private final JButton stepIntoButton;
    private final JButton stepOutButton;
    private final JTree tree;

    public DebugTreePanel(DebugContext context) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(Styles.padding);
        this.setBackground(Styles.defaultPanelBackgroundColor);

        JPanel heading = new JPanel(new BorderLayout());
        heading.setBackground(Styles.defaultPanelBackgroundColor);
        JLabel title = new JLabel("Debugging");
        title.setFont(Styles.titleFont);
        heading.add(title, BorderLayout.WEST);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setBackground(Styles.defaultPanelBackgroundColor);
        stepIntoButton = new JButton("Step into");// (F5)
        stepIntoButton.setFont(Styles.labelFont);
        controlPanel.add(stepIntoButton);

        stepOverButton = new JButton("Step over");// (F6)
        stepOverButton.setFont(Styles.labelFont);
        controlPanel.add(stepOverButton);

        stepOutButton = new JButton("Step out");// (F7)
        stepOutButton.setFont(Styles.labelFont);
        controlPanel.add(stepOutButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(Styles.labelFont);
        controlPanel.add(resetButton);
        heading.add(controlPanel, BorderLayout.EAST);
        this.add(heading);

        tree = new JTree(context.getRoot());
        tree.setCellRenderer(new NodeRendererResolver(context));
        JTreeUtils.expandAllNodes(tree);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(preferredSize);
        this.add(scrollPane);

    }

    public JTree getTree() {
        return tree;
    }

    public final void setTreeModel(TreeModel model) {
        tree.setModel(model);
    }

    public void setTreeSelectionListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }

    public void setResetButtonListener(ActionListener listener) {
        clearButtonListeners(resetButton);
        resetButton.addActionListener(listener);
    }

    public void setStepOverButtonListener(ActionListener listener) {
        clearButtonListeners(stepOverButton);
        stepOverButton.addActionListener(listener);
    }

    public void setStepIntoButtonListener(ActionListener listener) {
        clearButtonListeners(stepIntoButton);
        stepIntoButton.addActionListener(listener);
    }

    public void setStepOutButtonListener(ActionListener listener) {
        clearButtonListeners(stepOutButton);
        stepOutButton.addActionListener(listener);
    }

    private void clearButtonListeners(JButton button) {
        ActionListener[] actionListeners = button.getActionListeners();
        for (ActionListener listener : actionListeners) {
            button.removeActionListener(listener);
        }
    }

}
