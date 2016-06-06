/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package model.node;

import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author sdaskalov
 */
public interface Node extends TreeNode {

    String getId();

    String getName();

    String getRole();

    String getDescription();
    
    @Override
    Node getParent();

    List<Node> getChildren();
}
