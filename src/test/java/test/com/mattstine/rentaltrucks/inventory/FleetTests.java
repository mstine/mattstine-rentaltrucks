package test.com.mattstine.rentaltrucks.inventory;

import com.mattstine.rentaltrucks.events.Event;
import com.mattstine.rentaltrucks.events.EventLog;
import com.mattstine.rentaltrucks.fleet.Fleet;
import com.mattstine.rentaltrucks.fleet.Store;
import com.mattstine.rentaltrucks.fleet.StoreAddedEvent;
import com.mattstine.rentaltrucks.fleet.Truck;
import org.junit.Before;
import org.junit.Test;
import test.com.mattstine.rentaltrucks.support.VerifiableEventHandler;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Matt Stine
 */
public class FleetTests {

	private static final int TYPE_15_FOOT = 0;

	private EventLog eventLog;
	private Fleet fleet;

	@Before
	public void setUp() {
		eventLog = new EventLog();
		fleet = new Fleet(eventLog);
	}

	@Test
	public void canAddTruckToFleet() {
		fleet.createTruck(TYPE_15_FOOT);
		assertThat(fleet.trucksOnHand(), is(equalTo(1)));
	}

	@Test
	public void canAssignTruckToStore() {
		Store store = fleet.createStore();
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		fleet.assignTruck(truck, store);

		assertThat(store.hasTruck(truck), is(equalTo(true)));
	}

	@Test
	public void canAddStoreToFleet() {
		Store store = fleet.createStore();

		assertThat(store.getId(), notNullValue());
		assertThat(fleet.storesOnHand(), is(equalTo(1)));
	}

	@Test
	public void addingStoreFiresEvent() {
		VerifiableEventHandler handler = new VerifiableEventHandler() {
			@Override
			public void handleEvent(Event e) {
				invoked = true;
				assertThat(e, is(instanceOf(StoreAddedEvent.class)));
			}
		};

		eventLog.subscribe("hello", handler);
		fleet.createStore();

		assertThat(handler.isInvoked(), is(true));
	}

	@Test
	public void findTrucksByType() {
		fleet.createTruck(TYPE_15_FOOT);
		assertThat(fleet.findTrucksByType(TYPE_15_FOOT).size(), is(equalTo(1)));
	}

	@Test
	public void findTrucksByTypeAndStore() {
		Store store = fleet.createStore();
		assertThat(fleet.findTrucksByTypeAndStore(TYPE_15_FOOT, store).size(), is(equalTo(0)));

		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		fleet.assignTruck(truck, store);

		assertThat(fleet.findTrucksByTypeAndStore(TYPE_15_FOOT, store).size(), is(equalTo(1)));
	}

}
