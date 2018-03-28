package test.com.mattstine.rentaltrucks.support;

import com.mattstine.rentaltrucks.events.EventHandler;

/**
 * @author Matt Stine
 */
public abstract class VerifiableEventHandler implements EventHandler {
	protected boolean invoked = false;

	public boolean isInvoked() {
		return invoked;
	}
}
