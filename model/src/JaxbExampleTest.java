/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package com.ers.re.integration.exampleTest.jaxb;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ers.re.integration.exampleTest.jaxb.node.ListNode;
import com.ers.re.integration.exampleTest.jaxb.node.MapNode;
import com.ers.re.integration.exampleTest.jaxb.node.Node;
import com.ers.re.integration.exampleTest.jaxb.node.PropertyNode;
import com.ers.re.integration.exampleTest.jaxb.values.Condition;
import com.ers.re.integration.exampleTest.jaxb.values.Key;
import com.ers.re.integration.exampleTest.jaxb.values.Thing;
import com.ers.re.integration.exampleTest.jaxb.values.Value;

/**
 *
 * @author sdaskalov
 */
public class JaxbExampleTest {

	public static final Logger log = LoggerFactory.getLogger(JaxbExampleTest.class);
	private Node root;
	private static final PropertyNode property = new PropertyNode("I am a property", 12.34, true);

	public JaxbExampleTest() {
	}

	@Before
	public void setUp() {
//		MapNode mapNode = new MapNode();
//		mapNode.addMapEntry(new Key(1, "First entry"), new Value(Condition.POOR, "PC", "Very good, much buy!", 499.95));
//		mapNode.addMapEntry(new Key(2, "Second entry"), new Value(Condition.FAIR, "Tablet", "Never again.", 249.50));
//		mapNode.addMapEntry(new Key(3, "Third entry"), new Value(Condition.GOOD, "Smart phone", "Wow, such condition!", 1999.95));
//
//		ListNode listNode = new ListNode("Things I Love");
//		listNode.addThing(new Thing("JAXB", "Description is unneeded, who doesn't love it?"));
//		listNode.addThing(new Thing("Dead animals", "Because you are what you eat and I aspire to become a pig"));
//		listNode.addThing(new Thing("Pigs", "Already mentioned my affection towards this species"));
//
//		listNode.addChild(new PropertyNode("Too much, no buy", 0.0, false));
//		listNode.addChild(new PropertyNode("Free(ish) stuff!", 99.99, true));
//
//		PropertyNode propertyNode = new PropertyNode("I am your father", -9999.99, true);
//		propertyNode.addChild(mapNode);
//		propertyNode.addChild(listNode);
//		this.root = propertyNode;
	}
	
	@Test
	public void doSimpleTest() {
		try {
			marshal(property);
			unmarshal();
		} catch (JAXBException ex) {
			log.error("Derp", ex);
		}
	}

	//@Test
	public void doTest() {
		try {
			marshal(root);
			unmarshal();
		} catch (JAXBException ex) {
			log.error("Derp", ex);
		}
	}

	private void marshal(Object root) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(root.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(root, System.out);
	}

	private void unmarshal() {

	}
}
