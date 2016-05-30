/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node.impl.group;

import node.GroupNode;
import java.util.ArrayList;
import java.util.List;
import node.AbstractNode;
import node.Node;

/**
 *
 * @author sdaskalov
 */
abstract class AbstractGroupNode extends AbstractNode implements GroupNode {

	protected final List<Node> elements;

	public AbstractGroupNode() {
		this(new ArrayList<>());
	}

	public AbstractGroupNode(List<Node> elements) {
		super();
		this.elements = elements;
	}

	@Override
	public void addNode(Node child) {
		elements.add(child);
	}

}
