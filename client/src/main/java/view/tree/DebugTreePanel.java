/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import model.DebugContext;
import tree.JTreeUtils;
import tree.renderers.NodeRendererResolver;

/**
 *
 * @author sdaskalov
 */
public class DebugTreePanel extends JPanel {

    private static final Dimension preferredSize = new Dimension(new Dimension(600, 600));
    private final JButton resetButton;
    private final JButton stepOverButton;
    private final JButton stepIntoButton;
    private final JButton stepOutButton;
    private final JTree tree;

    public DebugTreePanel(DebugContext context) {
        resetButton = new JButton("Reset");
        resetButton.setActionCommand("RESET");
        this.add(resetButton);

        stepOverButton = new JButton("Step over");
        stepOverButton.setActionCommand("OVER");
        this.add(stepOverButton);

        stepIntoButton = new JButton("Step into");
        stepIntoButton.setActionCommand("INTO");
        this.add(stepIntoButton);

        stepOutButton = new JButton("Step out");
        stepOutButton.setActionCommand("OUT");
        this.add(stepOutButton);

        tree = new JTree(context.getRoot());
        tree.setCellRenderer(new NodeRendererResolver(context));
        JTreeUtils.expandAllNodes(tree);

        this.add(new JScrollPane(tree));
        this.setPreferredSize(preferredSize);
    }

    public JTree getTree() {
        return tree;
    }

    public final void setTreeModel(TreeModel model) {
        tree.setModel(model);
    }
    
    public void setTreeSelectionListener(TreeSelectionListener listener){
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
