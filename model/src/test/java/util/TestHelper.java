package util;

/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
import node.Node;
import node.impl.ConstantNode;
import node.impl.VariableNode;
import node.impl.binary.ExponentiationNode;
import node.impl.group.AverageNode;
import node.impl.group.ProductNode;
import node.impl.group.SumNode;
import simulation.StochasticVariableRegistry;
import variable.impl.ExponentialVariable;
import variable.impl.GammaVariable;
import variable.impl.GaussianVariable;
import variable.impl.LogNormalVariable;
import variable.impl.UniformVariable;

/**
 *
 * @author sdaskalov
 */
public class TestHelper {

    public static StochasticVariableRegistry makeVariableRegistry(int variableCount) {
        StochasticVariableRegistry variables = new StochasticVariableRegistry();
        for (int i = 1; i <= variableCount; i++) {
            GaussianVariable gaussian = new GaussianVariable("GAU" + i, i * 1L);
            gaussian.setMean(10.0 * i);
            gaussian.setDeviation(1.0 / i);
            variables.addVariable(gaussian);

            UniformVariable uniform = new UniformVariable("UNI" + i, i * 2L);
            uniform.setLowerBound(0);
            uniform.setUpperBound(20 * i);
            variables.addVariable(uniform);

            ExponentialVariable exp = new ExponentialVariable("EXP" + i, i * 3L);
            exp.setLambda(i * 1.0);
            variables.addVariable(exp);

            GammaVariable gamma = new GammaVariable("GAM" + i, i * 4L);
            gamma.setScale(3.0 / i);
            gamma.setShape(i / 3.0);
            variables.addVariable(gamma);

            LogNormalVariable logNormal = new LogNormalVariable("LOGN" + i, i * 5L);
            logNormal.setScale(3.0 / i);
            logNormal.setShape(i / 3.0);
            variables.addVariable(logNormal);
        }
        return variables;
    }

    public static Node buildNodeTree(int treeScale) {
        SumNode rootNode = new SumNode();
        for (int i = 1; i <= treeScale; i++) {
            ProductNode product = new ProductNode();
            product.addChild(new VariableNode("UNI" + i));
            AverageNode average = new AverageNode();
            average.addChild(new VariableNode("EXP" + i));
            average.addChild(new VariableNode("GAM" + i));
            average.addChild(new VariableNode("LOGN" + i));
            average.addChild(new ConstantNode(100.0, "Raise the average a little"));
            product.addChild(average);
            product.addChild(new ExponentiationNode(
                    new VariableNode("GAU" + i),
                    new ConstantNode(2.0)
            ));
            rootNode.addChild(product);
        }
        rootNode.setDescription("This is the sum of all the trucks I give");
        return rootNode;
    }

    public static Node buildDeepNodeTree(int depth, int breadth) {
        return buildDeepNodeTree(depth, breadth, 1);
    }

    private static Node buildDeepNodeTree(int depth, int breadth, int varIndex) {
        SumNode sum = new SumNode();
        if (depth != 2) {
            for (int i = 1; i <= breadth; i++) {
                sum.addChild(buildDeepNodeTree(depth - 1, breadth, i));
            }
        } else {
            sum.addChild(new VariableNode("GAU" + varIndex));
            sum.addChild(new VariableNode("UNI" + varIndex));
            sum.addChild(new VariableNode("EXP" + varIndex));
            sum.addChild(new VariableNode("GAM" + varIndex));
            sum.addChild(new VariableNode("LOGN" + varIndex));
        }
        return sum;
    }
}
