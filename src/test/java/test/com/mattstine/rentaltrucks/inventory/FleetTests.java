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

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(fleet.trucksOnHand()).isEqualTo(1);
	}

	@Test
	public void canAssignTruckToStore() {
		Store store = fleet.createStore();
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		fleet.assignTruck(truck, store);

		assertThat(store.hasTruck(truck)).isTrue();
	}

	@Test
	public void canAddStoreToFleet() {
		Store store = fleet.createStore();

		assertThat(store.getId()).isNotNull();
		assertThat(fleet.storesOnHand()).isEqualTo(1);
	}

	@Test
	public void addingStoreFiresEvent() {
		VerifiableEventHandler handler = VerifiableEventHandler
				.of(e -> assertThat(e).isExactlyInstanceOf(StoreAddedEvent.class));

		eventLog.subscribe("stores", handler);
		fleet.createStore();

		assertThat(handler.isInvoked()).isTrue();
	}

	@Test
	public void findTrucksByType() {
		fleet.createTruck(TYPE_15_FOOT);
		assertThat(fleet.findTrucksByType(TYPE_15_FOOT).size()).isEqualTo(1);
	}

	@Test
	public void findTrucksByTypeAndStore() {
		Store store = fleet.createStore();
		assertThat(fleet.findTrucksByTypeAndStore(TYPE_15_FOOT, store)).hasSize(0);

		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		fleet.assignTruck(truck, store);

		assertThat(fleet.findTrucksByTypeAndStore(TYPE_15_FOOT, store)).hasSize(1);
	}

}
