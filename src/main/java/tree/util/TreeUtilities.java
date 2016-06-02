/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.util;

import java.util.Map;
import node.Node;
import tree.handlers.NodeCountingHandler;
import tree.handlers.NodeIndexResolvingHandler;
import tree.handlers.VariableIndexResolvingHandler;
import tree.walkers.NodeWalker;
import tree.walkers.ReflectiveNodeWalker;
import variable.registry.SampledVariableRegistry;

/**
 *
 * @author sdaskalov
 */
public class TreeUtilities {

    public static int getTreeSize(Node root) {
        NodeCountingHandler handler = new NodeCountingHandler();
        NodeWalker walker = new ReflectiveNodeWalker<>(handler);
        walker.walk(root);
        return handler.getNodeCount();
    }

    public static void resolveVariableNodeIndices(Node root, SampledVariableRegistry sampledVariableRegistry) {
        VariableIndexResolvingHandler handler = new VariableIndexResolvingHandler(sampledVariableRegistry);
        NodeWalker walker = new ReflectiveNodeWalker(handler);
        walker.walk(root);
    }

    public static Map<Integer, Node> resolveNodeIndices(Node root) {
        NodeIndexResolvingHandler handler = new NodeIndexResolvingHandler();
        NodeWalker walker = new ReflectiveNodeWalker<>(handler);
        walker.walk(root);
        return handler.getNodeRegistry();
    }
}
