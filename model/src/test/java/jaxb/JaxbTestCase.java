/*
 * Sivo Daskalov, Monte Carlo Simulation Debugger (2016)
 */
package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sdaskalov
 */
public class JaxbTestCase {

    protected static final Logger log = LoggerFactory.getLogger(JaxbTestCase.class);
    protected Marshaller marshaller;
    protected Unmarshaller unmarshaller;

    protected <T> T doMarhshalUnmarshal(T source, String url) {
        try {
            marshaller.marshal(source, new File(url));
            marshaller.marshal(source, System.out);
            return (T) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            return null;
        }
    }

    protected void setupJaxbContext(Class clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }
}
