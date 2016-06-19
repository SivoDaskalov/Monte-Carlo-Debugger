/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.renderers;

import tree.renderers.AbstractNodeRenderer;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import model.DebugContext;
import node.impl.VariableNode;
import model.DebuggedNode;

/**
 *
 * @author sdaskalov
 */
public class NodeRendererResolver implements TreeCellRenderer {

    private final Map<Class, AbstractNodeRenderer> renderers;
    private final AbstractNodeRenderer abstractNodeRenderer;
    private final TreeCellRenderer defaultNodeRenderer;

    public NodeRendererResolver(DebugContext context) {
        abstractNodeRenderer = new AbstractNodeRenderer(context);
        defaultNodeRenderer = new DefaultTreeCellRenderer();
        renderers = new HashMap<>();
        renderers.put(VariableNode.class, new VariableNodeRenderer(context));
    }

    public void setViewContext(DebugContext context) {
        renderers.values().forEach(r -> r.setContext(context));
        abstractNodeRenderer.setContext(context);
    }

    public TreeCellRenderer getRenderer(Object object) {
        DebuggedNode node = (DebuggedNode) object;
        TreeCellRenderer renderer = renderers.get(node.getNode().getClass());
        if (renderer != null) {
            return renderer;
        }
        if (DebuggedNode.class.isAssignableFrom(object.getClass())) {
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
