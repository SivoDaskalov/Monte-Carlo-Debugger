/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.loggers;

import simulation.logger.ValueLogger;
import java.util.HashMap;
import java.util.Map;

import node.Node;
import simulation.context.SimulationContext;

/**
 *
 * @author sdaskalov
 */
public class CompositeValueLogger implements ValueLogger {

	private final Map<Class, ValueLogger> loggers;

	public CompositeValueLogger() {
		loggers = new HashMap<>();
	}

	public void putValueLogger(ValueLogger logger) {
		loggers.put(logger.getClass(), logger);
	}

	public ValueLogger getValueLogger(Class clazz) {
		return loggers.get(clazz);
	}

	@Override
	public void logValue(Node node, Double value, SimulationContext context) {
		loggers.forEach((k, v) -> {
			v.logValue(node, value, context);
		});
	}

}
