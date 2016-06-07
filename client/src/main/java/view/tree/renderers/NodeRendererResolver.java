/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view.tree.renderers;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import node.impl.AbstractNode;
import node.impl.ConstantNode;
import node.impl.VariableNode;

/**
 *
 * @author sdaskalov
 */
public class NodeRendererResolver implements TreeCellRenderer {

    private static final Map<Class, TreeCellRenderer> renderers;
    private static final AbstractNodeRenderer abstractNodeRenderer;
    private static final TreeCellRenderer defaultNodeRenderer;

    static {
        abstractNodeRenderer = new AbstractNodeRenderer();
        defaultNodeRenderer = new DefaultTreeCellRenderer();
        renderers = new HashMap<>();
        renderers.put(ConstantNode.class, new ConstantNodeRenderer(abstractNodeRenderer));
        renderers.put(VariableNode.class, new VariableNodeRenderer(abstractNodeRenderer));
    }

    public static TreeCellRenderer getRenderer(Object object) {
        TreeCellRenderer renderer = renderers.get(object.getClass());
        if (renderer != null) {
            return renderer;
        }
        if (AbstractNode.class.isAssignableFrom(object.getClass())) {
            return abstractNodeRenderer;
        }
        return defaultNodeRenderer;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return getRenderer(((DefaultMutableTreeNode) value).getUserObject())
                .getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }

}
