package test.com.mattstine.rentaltrucks.catalog;

import com.mattstine.rentaltrucks.catalog.Catalog;
import com.mattstine.rentaltrucks.catalog.CatalogItem;
import com.mattstine.rentaltrucks.catalog.CatalogItemAddedEvent;
import com.mattstine.rentaltrucks.events.Event;
import com.mattstine.rentaltrucks.events.EventLog;
import org.junit.Before;
import org.junit.Test;
import test.com.mattstine.rentaltrucks.support.VerifiableEventHandler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class CatalogTests {

	private Catalog catalog;
	private EventLog eventLog;

	@Before
	public void setUp() {
		eventLog = new EventLog();
		catalog = new Catalog(eventLog);
	}

	@Test
	public void canCreateCatalogItem() {
		catalog.add(new CatalogItem());

		assertThat(catalog.size(), is(equalTo(1)));
	}

	@Test
	public void addingCatalogItemFiresEvent() {
		VerifiableEventHandler handler = new VerifiableEventHandler() {
			@Override
			public void handleEvent(Event e) {
				invoked = true;
				assertThat(e, is(instanceOf(CatalogItemAddedEvent.class)));
			}
		};

		eventLog.subscribe(handler);
		catalog.add(new CatalogItem());

		assertThat(handler.isInvoked(), is(true));
	}

}
