/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.service.SimulationService;

/**
 *
 * @author sdaskalov
 */
public class SimulationServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(SimulationServiceClient.class);
    private static final String defaultHost = "localhost";
    private static final String defaultPort = "9999";
    private SimulationService simulationService;

    public SimulationServiceClient() {
        this(defaultHost, defaultPort);
    }

    public SimulationServiceClient(String host, String port) {
        try {
            URL url = new URL("http://" + host + ":" + port + "/SimulationService?wsdl");
            QName qname = new QName("http://service.simulation/", "SimulationServiceImplService");
            Service service = Service.create(url, qname);
            simulationService = service.getPort(SimulationService.class);
        } catch (MalformedURLException ex) {
            logger.error("Malformed URL", ex);
        }
    }

    public SimulationResponse simulate(SimulationRequest request) {
        if (simulationService != null) {
            return simulationService.simulate(request);
        }
        return null;
    }

}
