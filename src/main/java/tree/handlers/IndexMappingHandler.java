/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.handlers;

import java.util.HashMap;
import java.util.Map;
import node.impl.AbstractNode;
import node.Node;

/**
 *
 * @author sdaskalov
 */
public class IndexMappingHandler implements NodeHandler<AbstractNode> {

    private final Map<Integer, Node> registry;
    private int nextIndex;

    public IndexMappingHandler() {
        registry = new HashMap<>();
        nextIndex = 0;
    }

    public Map<Integer, Node> getNodeRegistry() {
        return registry;
    }

    @Override
    public void handle(AbstractNode node) {
        int currentIndex = nextIndex++;
        registry.put(currentIndex, node);
        node.setNodeIndex(currentIndex);
    }

}
