/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.renderers;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import model.DebugContext;
import model.DebuggedNode;
import node.Node;
import view.Styles;

/**
 *
 * @author sdaskalov
 */
public class AbstractNodeRenderer implements TreeCellRenderer {

    protected DebugContext context;
    private final JLabel valueLabel;
    private final JLabel roleLabel;
    private final JLabel descriptionLabel;

    public AbstractNodeRenderer(DebugContext context) {
        this.context = context;
        valueLabel = makeLabel("=", Styles.labelFont, Styles.labelColor);
        roleLabel = makeLabel("role =", Styles.labelFont, Styles.labelColor);
        descriptionLabel = makeLabel("description =", Styles.labelFont, Styles.labelColor);
    }

    protected final JLabel makeLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        if (font != null) {
            label.setFont(font);
        }
        if (color != null) {
            label.setForeground(color);
        }
        return label;
    }

    private String getNodeName(Node node) {
        String className = node.getClass().getSimpleName();
        return className.trim().replace("Node", "");
    }

    private JLabel getName(Node node) {
        return makeLabel(getNodeName(node), Styles.valueFont, Styles.valueColor);
    }

    private JLabel getValue(Node node) {
        String id = node.getId();
        Double value = context.getValue(id);
        if (value == null) {
            return null;
        }
        return makeLabel(String.format(Styles.valueFormat, value), Styles.valueFont, Styles.valueColor);
    }

    private JLabel getRole(Node node) {
        String role = node.getRole();
        if (role == null || role.equals("")) {
            return null;
        }
        return makeLabel(role, Styles.valueFont, Styles.valueColor);
    }

    private JLabel getDescription(Node node) {
        String description = node.getDescription();
        if (description == null || description.equals("")) {
            return null;
        }
        return makeLabel(description, Styles.valueFont, Styles.valueColor);
    }

    private void fillPanel(JPanel panel, DebuggedNode debuggedNode) {
        Node node = debuggedNode.getNode();
        panel.add(getName(node));
        if (debuggedNode.isValueVisible() == true) {
            JLabel value = getValue(node);
            if (value != null) {
                panel.add(valueLabel);
                panel.add(value);
                panel.add(Box.createHorizontalStrut(Styles.strut));
            }
        }
        JLabel role = getRole(node);
        if (role != null) {
            panel.add(roleLabel);
            panel.add(role);
            panel.add(Box.createHorizontalStrut(Styles.strut));
        }
        JLabel description = getDescription(node);
        if (description != null) {
            panel.add(descriptionLabel);
            panel.add(description);
            panel.add(Box.createHorizontalStrut(Styles.strut));
        }
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;

        DebuggedNode node = (DebuggedNode) treeNode.getUserObject();
        JPanel panel = new JPanel();
        fillPanel(panel, node);
        if (selected) {
            panel.setBackground(Styles.selectedBackgroundColor);
        } else {
            panel.setBackground(Styles.defaultNodeBackgroundColor);
        }
        DefaultMutableTreeNode currentlyDebuggedTreeNode = context.getCurrentlyDebuggedNode();
        if (currentlyDebuggedTreeNode != null) {
            String currentlyDebuggedNodeId
                    = ((DebuggedNode) currentlyDebuggedTreeNode.getUserObject())
                    .getNode()
                    .getId();
            if (node.getNode().getId().equals(currentlyDebuggedNodeId)) {
                panel.setBackground(Styles.currentlyDebuggedBackgroundColor);
            }
        }
        return panel;
    }

}
