package test.com.mattstine.rentaltrucks.events;

import com.mattstine.rentaltrucks.events.Event;
import com.mattstine.rentaltrucks.events.EventLog;
import org.junit.Before;
import org.junit.Test;
import test.com.mattstine.rentaltrucks.support.VerifiableEventHandler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class EventLogTests {

	private EventLog eventLog;

	@Before
	public void setUp() {
		this.eventLog = new EventLog();
	}

	@Test
	public void shouldAddSubscriber() {
		eventLog.subscribe("hello", System.out::println);

		assertThat(eventLog.getNumberOfSubscribers("hello"), is(equalTo(1)));
	}

	@Test
	public void shouldInvokeSubscribersOnPublish() {
		VerifiableEventHandler handler = new VerifiableEventHandler() {
			@Override
			public void handleEvent(Event e) {
				this.invoked = true;
			}
		};

		eventLog.subscribe("hello", handler);
		eventLog.publish("hello", new Event());

		assertThat(handler.isInvoked(), is(true));
	}
}
