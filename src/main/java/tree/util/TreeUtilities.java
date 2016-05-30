/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.util;

import java.util.Map;
import node.Node;
import tree.handlers.IndexMappingHandler;
import tree.handlers.NodeCountingHandler;
import tree.walkers.NodeWalker;
import tree.walkers.ReflectiveNodeWalker;

/**
 *
 * @author sdaskalov
 */
public class TreeUtilities {

    public static Map<Integer, Node> resolveNodeIndices(Node root) {
        IndexMappingHandler handler = new IndexMappingHandler();
        NodeWalker walker = new ReflectiveNodeWalker<>(handler);
        walker.walk(root);
        return handler.getNodeRegistry();
    }

    public static int getTreeSize(Node root) {
        NodeCountingHandler handler = new NodeCountingHandler();
        NodeWalker walker = new ReflectiveNodeWalker<>(handler);
        walker.walk(root);
        return handler.getNodeCount();
    }
    
}
