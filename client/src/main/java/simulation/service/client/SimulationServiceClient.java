/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.service.client;

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
    private String host;
    private String port;

    public SimulationServiceClient() {
        this(defaultHost, defaultPort);
    }

    public SimulationServiceClient(String host, String port) {
        this.host = host;
        this.port = port;
        setup();
    }

    private void setup() {
        try {
            URL url = new URL("http://" + host + ":" + port + "/SimulationService?wsdl");
            QName qname = new QName("http://service.simulation/", "SimulationServiceImplService");
            Service service = Service.create(url, qname);
            simulationService = service.getPort(SimulationService.class);
        } catch (Exception ex) {
            logger.info("Unable to connect to service");
            simulationService = null;
        }
    }

    public boolean isConnected() {
        return simulationService != null;
    }

    public void reconnect() {
        try {
            setup();
        } catch (Exception ex) {
            // Nothing meaningful to do here
        }
    }

    public SimulationResponse simulate(SimulationRequest request) {
        if (simulationService != null) {
            return simulationService.simulate(request);
        }
        return null;
    }

    public SimulationResponse simulate(SimulationResponse response) {
        if (simulationService != null) {
            SimulationRequest request = new SimulationRequest(
                    response.getProperties(),
                    response.getVariableRegistry(),
                    response.getFormula());
            return simulationService.simulate(request);
        }
        return null;
    }

}
