/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package model.node;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author sdaskalov
 */
public class NodeRenderer implements TreeCellRenderer {

    private static final DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    private static final Color selected = new Color(100, 100, 100);
    private static final Color nonSelected = new Color(50, 50, 50);

    private final JLabel id;
    private final JLabel name;
    private final JLabel role;
    private final JLabel description;
    private final JLabel value;
    private final JPanel renderer;

    public NodeRenderer() {
        id = new JLabel();
        name = new JLabel();
        role = new JLabel();
        description = new JLabel();
        value = new JLabel();

        renderer = new JPanel();
        renderer.add(id);
        renderer.add(name);
        renderer.add(role);
        renderer.add(description);
        renderer.add(value);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component result = null;
        if ((value != null) && (value instanceof Node)) {
            Node node = (Node)value;
            id.setText(node.getId());
                   
            result = renderer;
        }
        if (result == null) {
            result = defaultRenderer.getTreeCellRendererComponent(tree, value, leaf, expanded, leaf, row, hasFocus);
        }
        return result;
    }

}
