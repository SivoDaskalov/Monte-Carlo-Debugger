/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.renderers;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import node.impl.VariableNode;
import tree.DebuggedNode;
import view.styles.Styles;

/**
 *
 * @author sdaskalov
 */
public class VariableNodeRenderer extends AbstractNodeRenderer {

    public VariableNodeRenderer(DebugContext context) {
        super(context);
    }

    @Override
    public JPanel getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JPanel result = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
        DebuggedNode node = (DebuggedNode) treeNode.getUserObject();
        VariableNode variable = (VariableNode) node.getNode();
        result.add(makeLabel(variable.getName(), Styles.valueFont, Styles.valueColor), 1);
        return result;
    }

}
