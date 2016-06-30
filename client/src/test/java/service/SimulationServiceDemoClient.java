/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package service;

import java.net.URL;
import java.util.Arrays;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import node.impl.VariableNode;
import simulation.SimulationProperties;
import simulation.SimulationRequest;
import simulation.SimulationResponse;
import simulation.StochasticVariableRegistry;
import simulation.service.SimulationService;
import variable.impl.GaussianVariable;

/**
 *
 * @author sdaskalov
 */
public class SimulationServiceDemoClient {

    public static void main(String[] args) {
        try {
            String host = "localhost", port = "9999";
            URL url = new URL(String.format("http://%s:%s/SimulationService?wsdl", host, port));
            QName qname = new QName("http://service.simulation/", "SimulationServiceImplService");
            Service service = Service.create(url, qname);
            SimulationService simulationService = service.getPort(SimulationService.class);

            SimulationRequest request = new SimulationRequest(
                    new SimulationProperties("DemoUsage", "This is just an example", 5),
                    new StochasticVariableRegistry(
                            Arrays.asList(new GaussianVariable("X", 1L, 5.0, 0.3))
                    ),
                    new VariableNode("X")
            );

            SimulationResponse response = simulationService.simulate(request);
            System.out.println(Arrays.toString(response.getValues().get(0).getValues()));
        } catch (Exception ex) {
        }
    }
}
