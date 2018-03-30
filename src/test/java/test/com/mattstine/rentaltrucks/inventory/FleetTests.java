package test.com.mattstine.rentaltrucks.inventory;

import com.mattstine.rentaltrucks.fleet.Fleet;
import com.mattstine.rentaltrucks.fleet.Store;
import com.mattstine.rentaltrucks.fleet.Truck;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Matt Stine
 */
public class FleetTests {

	private static final int TYPE_15_FOOT = 0;

	private Fleet fleet;

	@Before
	public void setUp() {
		fleet = new Fleet();
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
		fleet.createStore();

		assertThat(fleet.storesOnHand(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksByType() {
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(0)));

		truck.setRentable(true);
		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksAtStore() {
		Store store = fleet.createStore();
		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(0)));

		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		fleet.assignTruck(truck, store);
		truck.setRentable(true);

		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(1)));
	}

}
