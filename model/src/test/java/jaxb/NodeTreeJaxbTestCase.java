package jaxb;

/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */


import org.junit.Before;
import org.junit.Test;
import simulation.NodeTreeWrapper;
import util.TestHelper;

/**
 *
 * @author sdaskalov
 */
public class NodeTreeJaxbTestCase extends JaxbTestCase {

    private NodeTreeWrapper root;

    @Before
    public void setUp() {
        setupJaxbContext(NodeTreeWrapper.class);
        root = new NodeTreeWrapper(TestHelper.buildNodeTree(3));
    }

    @Test
    public void testRequestMarshalling() {
        NodeTreeWrapper unmarshaled = doMarhshalUnmarshal(root, "target/FormulaTree.xml");
    }
}
