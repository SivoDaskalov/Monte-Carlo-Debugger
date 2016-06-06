/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package view;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import model.node.Node;

/**
 *
 * @author sdaskalov
 */
public class FormulaTreePanel extends JPanel {

    private JTree formulaTree;

    public FormulaTreePanel(Node node) {
        DefaultTreeModel model = new DefaultTreeModel(node);
        formulaTree = new JTree(model);
    }

}
