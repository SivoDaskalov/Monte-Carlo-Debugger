/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package calculation;

import java.util.Arrays;
import node.impl.ConstantNode;
import node.impl.binary.DivisionNode;
import node.impl.binary.LogarithmNode;
import node.impl.binary.RootNode;
import node.impl.group.ProductNode;
import node.impl.group.SumNode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
public class SimpleCalculationTestCase {

    private static Logger log = LoggerFactory.getLogger(SimpleCalculationTestCase.class);

    public final SumNode rootNode;

    public SimpleCalculationTestCase() {
        rootNode = new SumNode();
        for (int i = 0; i < 5; i++) {
            ProductNode product = new ProductNode();
            for (int j = 0; j < 5; j++) {
//                product.addNode(new VariableNode());
            }
            rootNode.addNode(product);
        }
    }

    @Test
    public void simpleCalculation() {
//        System.out.println("Root: " + rootNode.getValue());
    }

    @Test
    public void nanCalculation() {
        DivisionNode division = new DivisionNode(new ConstantNode(5.0), new ConstantNode(0.0));
        LogarithmNode logarithm = new LogarithmNode(new ConstantNode(5.0), new ConstantNode(-5.0));
        RootNode root = new RootNode(new ConstantNode(-5.0), new ConstantNode(2.0));
        SumNode sum = new SumNode(Arrays.asList(new ConstantNode(5.0), division, logarithm, root));
//        log.info("" + sum.getValue());
    }
}
