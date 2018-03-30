package test.com.mattstine.rentaltrucks.events;

import com.mattstine.rentaltrucks.events.Event;
import com.mattstine.rentaltrucks.events.EventLog;
import org.junit.Before;
import org.junit.Test;
import test.com.mattstine.rentaltrucks.support.VerifiableEventHandler;

import static org.assertj.core.api.Assertions.assertThat;

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
		eventLog.subscribe("some-topic", System.out::println);

		assertThat(eventLog.getNumberOfSubscribers("some-topic")).isEqualTo(1);
	}

	@Test
	public void shouldInvokeSubscribersOnPublish() {
		VerifiableEventHandler handler = new VerifiableEventHandler() {
			@Override
			public void handleEvent(Event e) {
				this.invoked = true;
			}
		};

		eventLog.subscribe("some-topic", handler);
		eventLog.publish("some-topic", new Event());

		assertThat(handler.isInvoked()).isTrue();
	}
}
