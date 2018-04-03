package test.com.mattstine.rentaltrucks.support;

import com.mattstine.rentaltrucks.events.Event;
import com.mattstine.rentaltrucks.events.EventHandler;

/**
 * @author Matt Stine
 */
public abstract class VerifiableEventHandler implements EventHandler {
	protected boolean invoked = false;

	public boolean isInvoked() {
		return invoked;
	}



	public static VerifiableEventHandler of(EventHandler eventHandler) {


		return new VerifiableEventHandler() {
			@Override
			public void handleEvent(Event e) {
				invoked = true;
				eventHandler.handleEvent(e);
			}
		};
	}
}
