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
	private static final int TYPE_24_FOOT = 1;

	private Fleet fleet;
	private Store store;

	// Fleet keeps track of all the trucks we have at the company

	@Before
	public void setUp() {
		fleet = new Fleet();
		store = new Store();
	}

	@Test
	public void canAddTruckToInventory() {
		fleet.addTruck(new Truck(TYPE_15_FOOT, false, store));

		assertThat(fleet.trucksOnHand(), is(equalTo(1)));
	}

	@Test
	public void canAddStoreToInventory() {
		fleet.addStore(new Store());

		assertThat(fleet.storesOnHand(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksByType() {
		fleet.addTruck(new Truck(TYPE_24_FOOT, true, store));

		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(0)));

		fleet.addTruck(new Truck(TYPE_15_FOOT,true, store));

		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksAtStore() {
		fleet.addTruck(new Truck(TYPE_24_FOOT, true, store));

		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(0)));

		fleet.addTruck(new Truck(TYPE_15_FOOT, true, store));
		fleet.addTruck(new Truck(TYPE_15_FOOT, false, store));
		fleet.addTruck(new Truck(TYPE_15_FOOT, true, null));

		assertThat(fleet.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(1)));
	}

}
