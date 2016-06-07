/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import node.Node;
import view.context.ViewContext;

/**
 *
 * @author sdaskalov
 */
public class AbstractNodeRenderer implements TreeCellRenderer {

    private static final int strut = 30;
    private static final String valueFormat = "%.5f";
    protected static final Font valueFont = new Font("Arial", Font.PLAIN, 14);
    protected static final Color valueColor = Color.BLACK;

    protected static final Font labelFont = new Font("Arial", Font.ITALIC, 14);
    protected static final Color labelColor = new Color(80, 80, 80);

    private static final Color defaultBackgroundColor = Color.WHITE;
    private static final Color selectedBackgroundColor = Color.LIGHT_GRAY;

    protected ViewContext context;
    private final JLabel valueLabel;
    private final JLabel roleLabel;
    private final JLabel descriptionLabel;

    public AbstractNodeRenderer(ViewContext context) {
        this.context = context;
        valueLabel = makeLabel("=", labelFont, labelColor);
        roleLabel = makeLabel("role =", labelFont, labelColor);
        descriptionLabel = makeLabel("description =", labelFont, labelColor);
    }

    public void setContext(ViewContext context) {
        this.context = context;
    }

    protected JLabel makeLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        if (font != null) {
            label.setFont(font);
        }
        if (color != null) {
            label.setForeground(color);
        }
        return label;
    }

    public String getNodeName(Node node) {
        String className = node.getClass().getSimpleName();
        return className.trim().replace("Node", "");
    }

    public JLabel getName(Node node) {
        return makeLabel(getNodeName(node), valueFont, valueColor);
    }

    public JLabel getValue(Node node) {
        String id = node.getId();
        Double value = context.getValue(id);
        if (value == null) {
            return null;
        }
        return makeLabel(String.format(valueFormat, value), valueFont, valueColor);
    }

    public JLabel getRole(Node node) {
        String role = node.getRole();
        if (role == null || role.equals("")) {
            return null;
        }
        return makeLabel(role, valueFont, valueColor);
    }

    public JLabel getDescription(Node node) {
        String description = node.getDescription();
        if (description == null || description.equals("")) {
            return null;
        }
        return makeLabel(description, valueFont, valueColor);
    }

    private void fillPanel(JPanel panel, Node node) {
        panel.add(getName(node));
        JLabel value = getValue(node);
        if (value != null) {
            panel.add(valueLabel);
            panel.add(value);
            panel.add(Box.createHorizontalStrut(strut));
        }
        JLabel role = getRole(node);
        if (role != null) {
            panel.add(roleLabel);
            panel.add(role);
            panel.add(Box.createHorizontalStrut(strut));
        }
        JLabel description = getDescription(node);
        if (description != null) {
            panel.add(descriptionLabel);
            panel.add(description);
            panel.add(Box.createHorizontalStrut(strut));
        }
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        Node node = (Node) treeNode.getUserObject();
        JPanel panel = new JPanel();
        if (selected) {
            panel.setBackground(selectedBackgroundColor);
        } else {
            panel.setBackground(defaultBackgroundColor);
        }
        fillPanel(panel, node);
        return panel;
    }

}
