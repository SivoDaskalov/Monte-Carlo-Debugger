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
import simulation.SimulationResponse;

/**
 *
 * @author sdaskalov
 */
public class JaxbUtils {

    protected static final Logger log = LoggerFactory.getLogger(JaxbUtils.class);

    private static final JaxbUtils instance = new JaxbUtils();

    JaxbUtils() {
        try {
            JAXBContext context = JAXBContext.newInstance(SimulationResponse.class);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            log.error("JAXB context creation error", ex);
        }
    }

    public static JaxbUtils getInstance() {
        return instance;
    }

    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public SimulationResponse unmarshal(String url) {
        try {
            return (SimulationResponse) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            return null;
        }
    }

    public void marshal(SimulationResponse response, String url) {
        try {
            marshaller.marshal(response, new File(url));
        } catch (JAXBException ex) {
        }
    }
}
