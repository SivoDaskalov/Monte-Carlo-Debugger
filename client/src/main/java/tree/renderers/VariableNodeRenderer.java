/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package tree.renderers;

import tree.renderers.AbstractNodeRenderer;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.DebugContext;
import node.impl.VariableNode;
import model.DebuggedNode;
import view.Styles;

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
