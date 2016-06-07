/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package treeview;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.response.SimulationResponseImpl;

/**
 *
 * @author sdaskalov
 */
public class SimulationResponseUnmarshaler {

    protected static final Logger log = LoggerFactory.getLogger(SimulationResponseUnmarshaler.class);
    private static Unmarshaller unmarshaller;

    static {
        try {
            JAXBContext context = JAXBContext.newInstance(SimulationResponseImpl.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException ex) {
            log.error("JAXB context error", ex);
        }
    }

    public static SimulationResponseImpl unmarshal(String url) {
        try {
            return (SimulationResponseImpl) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            log.error("Marshalling error", ex);
            return null;
        }
    }

}
