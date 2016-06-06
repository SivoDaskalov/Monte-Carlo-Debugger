/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package tree.handlers;

import node.Node;

/**
 *
 * @author sdaskalov
 */
public interface NodeHandler<AbstractNode extends Node> {

    void handle(AbstractNode node);
}
