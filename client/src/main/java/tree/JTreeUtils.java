/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree;

import javax.swing.JTree;

/**
 *
 * @author sdaskalov
 */
public class JTreeUtils {

    public static void expandAllNodes(JTree tree) {
        expandAllNodes(tree, 0, tree.getRowCount());
    }

    private static void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }
}
