/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package simulation.listeners;

import simulation.listener.SimulationCompletionListener;
import java.util.concurrent.CountDownLatch;

import simulation.manager.SimulationManager;

/**
 *
 * @author sdaskalov
 */
public class LatchLoweringCompletionListener implements SimulationCompletionListener {

	private final CountDownLatch latch;

	public LatchLoweringCompletionListener(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void notify(SimulationManager manager) {
		latch.countDown();
	}

}
