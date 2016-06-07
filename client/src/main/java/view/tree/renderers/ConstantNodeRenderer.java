/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import node.impl.ConstantNode;

/**
 *
 * @author sdaskalov
 */
public class ConstantNodeRenderer implements TreeCellRenderer {

    private final AbstractNodeRenderer abstractNodeRenderer;

    public ConstantNodeRenderer(AbstractNodeRenderer abstractNodeRenderer) {
        this.abstractNodeRenderer = abstractNodeRenderer;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JPanel result = abstractNodeRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        ConstantNode node = (ConstantNode) treeNode.getUserObject();
        result.add(new JLabel(" = "+node.getValue()));
        return result;
    }

}
