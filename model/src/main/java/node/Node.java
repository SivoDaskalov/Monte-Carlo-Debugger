/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package node;

import simulation.interfaces.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public interface Node {

    String getId();

    String getRole();

    void setRole(String role);

    String getDescription();

    void setDescription(String description);

    double getValue(SimulationContext context);
}
