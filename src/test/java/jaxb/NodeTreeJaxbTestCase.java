/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static jaxb.JaxbTestCase.log;
import org.junit.Before;
import org.junit.Test;
import simulation.NodeTreeWrapper;
import testutils.BuildHelper;

/**
 *
 * @author sdaskalov
 */
public class NodeTreeJaxbTestCase extends JaxbTestCase {

    private NodeTreeWrapper root;

    @Before
    public void setUp() {
        try {
            JAXBContext context = JAXBContext.newInstance(NodeTreeWrapper.class);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
        root = new NodeTreeWrapper(BuildHelper.buildNodeTree(3));
    }

    @Test
    public void testRequestMarshalling() {
        NodeTreeWrapper unmarshalled = doMarhshalUnmarshal(root, "target/FormulaTree.xml");
    }
}
