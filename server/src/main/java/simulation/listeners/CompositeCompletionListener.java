/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.listeners;

import java.util.HashMap;
import java.util.Map;

import simulation.interfaces.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public class CompositeCompletionListener implements SimulationCompletionListener {

	private final Map<Class, SimulationCompletionListener> listeners;

	public CompositeCompletionListener() {
		listeners = new HashMap<>();
	}

	public void putCompletionListener(SimulationCompletionListener listener) {
		listeners.put(listener.getClass(), listener);
	}

	public SimulationCompletionListener getCompletionListener(Class clazz) {
		return listeners.get(clazz);
	}

	@Override
	public void notify(SimulationManager manager) {
		listeners.forEach((k, v) -> {
			v.notify(manager);
		});
	}
}
