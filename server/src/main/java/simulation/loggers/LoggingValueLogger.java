/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.loggers;

import simulation.logger.ValueLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class LoggingValueLogger implements ValueLogger {

    private final Logger log;

    public LoggingValueLogger() {
        log = LoggerFactory.getLogger(LoggingValueLogger.class);
    }

    public LoggingValueLogger(Logger log) {
        this.log = log;
    }

    @Override
    public void logValue(Node node, Double value, SimulationContext context) {
        log.debug(node.getClass().getSimpleName() + "\t   Run:" + context.getRunNumber()
                + "\t   Node:" + node.getId() + "\t   Value:" + value);
    }

}
