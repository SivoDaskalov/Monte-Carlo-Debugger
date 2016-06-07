/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import node.impl.VariableNode;
import view.context.ViewContext;

/**
 *
 * @author sdaskalov
 */
public class VariableNodeRenderer extends AbstractNodeRenderer {

    public VariableNodeRenderer(ViewContext context) {
        super(context);
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JPanel result = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        VariableNode node = (VariableNode) treeNode.getUserObject();
        result.add(makeLabel(node.getName(), valueFont, valueColor), 1);
        return result;
    }

}
