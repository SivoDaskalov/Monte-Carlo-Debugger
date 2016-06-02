/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static jaxb.JaxbTestCase.log;
import node.Node;
import node.impl.AbstractNode;
import org.junit.Before;
import org.junit.Test;
import testutils.BuildHelper;

/**
 *
 * @author sdaskalov
 */
public class FormulaTreeJaxbTestCase extends JaxbTestCase {

    private Node root;

    @Before
    public void setUp() {
        try {
            JAXBContext context = JAXBContext.newInstance(AbstractNode.class);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
        root = BuildHelper.buildNodeTree(3);
    }

    @Test
    public void testRequestMarshalling() {
        Node unmarshalled = doMarhshalUnmarshal(root, "target/FormulaTree.xml");
    }
}
