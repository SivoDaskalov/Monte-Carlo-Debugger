/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import node.Node;

/**
 *
 * @author sdaskalov
 */
public class AbstractNodeRenderer implements TreeCellRenderer {

    private int maxRowWidth = 600;
    private int rowHeight = 30;

    public AbstractNodeRenderer() {
    }

    public String getNodeName(Node node) {
        String className = node.getClass().getSimpleName();
        return className.replace("Node", "");
    }

    public JLabel getName(Node node) {
        return new JLabel(getNodeName(node));
    }

    public JLabel getRole(Node node) {
        String role = node.getRole();
        if (role == null || role.equals("")) {
            return new JLabel("");
        } else {
            return new JLabel("role = " + role);
        }
    }

    public JLabel getDescription(Node node) {
        String description = node.getDescription();
        if (description == null || description.equals("")) {
            return new JLabel("");
        } else {
            return new JLabel("description = " + description);
        }
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        Node node = (Node) treeNode.getUserObject();
        treeNode.getDepth();
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(getName(node));
        panel.add(getRole(node));
        panel.add(getDescription(node));
        return panel;
    }

}
