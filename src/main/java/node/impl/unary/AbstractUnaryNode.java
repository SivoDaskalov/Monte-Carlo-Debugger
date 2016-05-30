/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.unary;

import node.AbstractNode;
import node.Node;

/**
 *
 * @author sdaskalov
 */
abstract class AbstractUnaryNode extends AbstractNode {

	protected final Node argument;

	public AbstractUnaryNode(Node argument) {
		super();
		this.argument = argument;
	}

}
