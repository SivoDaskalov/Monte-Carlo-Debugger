/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import simulation.SimulationRequest;
import simulation.SimulationResponse;

/**
 *
 * @author sdaskalov
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface SimulationService {

    @WebMethod
    SimulationResponse simulate(SimulationRequest request);
}
