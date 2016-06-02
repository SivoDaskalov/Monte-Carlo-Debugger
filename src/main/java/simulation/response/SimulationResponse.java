/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.response;

import simulation.request.SimulationRequest;

/**
 *
 * @author sdaskalov
 */
public interface SimulationResponse extends SimulationRequest {

    public double[][] getValueRegistry();
}
