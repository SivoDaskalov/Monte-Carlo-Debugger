/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package testutils;

import node.Node;
import node.impl.VariableNode;
import node.impl.group.ProductNode;
import node.impl.group.SumNode;
import variable.impl.ExponentialVariable;
import variable.impl.GaussianVariable;
import variable.impl.UniformVariable;
import variable.registry.StochasticVariableRegistryImpl;

/**
 *
 * @author sdaskalov
 */
public class BuildHelper {

    public static StochasticVariableRegistryImpl makeVariableRegistry(int variableCount) {
        StochasticVariableRegistryImpl variables = new StochasticVariableRegistryImpl();
        for (int i = 0; i < variableCount; i++) {
            double from = i * 10.0;
            double to = i * 10.0 + 10.0;
            variables.putVariable(new GaussianVariable("X" + i, 1L, (from + to) / 2.0, 1.0 / 3.0));
            variables.putVariable(new UniformVariable("Y" + i, 1L, from, to));
            variables.putVariable(new ExponentialVariable("Z" + i, 1L, 1.0));
        }
        return variables;
    }

    public static Node buildNodeTree(int treeScale) {
        SumNode rootNode = new SumNode();
        for (int i = 0; i < treeScale; i++) {
            ProductNode product = new ProductNode();
            product.addNode(new VariableNode("X" + i));
            product.addNode(new VariableNode("Y" + i));
            product.addNode(new VariableNode("Z" + i));
            rootNode.addNode(product);
        }
        return rootNode;
    }
}
