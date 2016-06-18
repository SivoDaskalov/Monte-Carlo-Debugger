/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationResponse;

/**
 *
 * @author sdaskalov
 */
public class SimulationUnmarshaller {

    protected static final Logger log = LoggerFactory.getLogger(SimulationUnmarshaller.class);

    private static final SimulationUnmarshaller instance = new SimulationUnmarshaller();

    public static SimulationUnmarshaller getInstance() {
        return instance;
    }

    private Unmarshaller unmarshaller;

    public SimulationUnmarshaller() {
        try {
            JAXBContext context = JAXBContext.newInstance(SimulationResponse.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException ex) {
            log.error("JAXB context creation error", ex);
        }
    }

    public SimulationResponse unmarshal(String url) {
        try {
            return (SimulationResponse) unmarshaller.unmarshal(new File(url));
        } catch (JAXBException ex) {
            return null;
        }
    }
}
