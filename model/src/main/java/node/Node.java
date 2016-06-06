/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node;

import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public interface Node {

	String getId();
        
        String getRole();

	String getDescription();

	double getValue(SimulationContext context);
}
