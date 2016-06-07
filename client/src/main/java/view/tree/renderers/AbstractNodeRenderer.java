/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import java.awt.LayoutManager;
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

    private LayoutManager layout;

    public AbstractNodeRenderer() {
    }

    public JLabel getName(Node node) {
        return null;
    }

    public JLabel getRole(Node node) {
        return null;
    }

    public JLabel getDescription(Node node) {
        return null;
    }

    public String getNodeName(Node node) {
        String className = node.getClass().getSimpleName();
        return className.replace("Node", "");
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        Node node = (Node) treeNode.getUserObject();
        JPanel panel = new JPanel();
        panel.add(new JLabel(getNodeName(node)));
        return panel;
    }

}
