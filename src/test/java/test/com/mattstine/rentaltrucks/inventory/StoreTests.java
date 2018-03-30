package test.com.mattstine.rentaltrucks.inventory;

import com.mattstine.rentaltrucks.fleet.Fleet;
import com.mattstine.rentaltrucks.fleet.Store;
import com.mattstine.rentaltrucks.fleet.Truck;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class StoreTests {

	private static final int TYPE_15_FOOT = 0;
	private Store store;
	private Fleet fleet;

	@Before
	public void setUp() {
		fleet = new Fleet();
		store = fleet.createStore();
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotManageTruckType() {
		store.trucksOnHand(TYPE_15_FOOT);
	}

	@Test
	public void canAddATruckToInventory() {
		store.addTruck(fleet.createTruck(TYPE_15_FOOT));
		assertThat(store.trucksOnHand(TYPE_15_FOOT), is(equalTo(1)));
	}

	@Test
	public void canRemoveATruckFromInventory() {
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		store.addTruck(truck);
		store.removeTruck(truck);
		assertThat(store.trucksOnHand(TYPE_15_FOOT), is(equalTo(0)));
	}

	@Test
	public void doesStoreHaveTruck() {
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		store.addTruck(truck);

		assertThat(store.hasTruck(truck), is(equalTo(true)));
	}

	@Test
	public void findRentableTrucks() {
		Truck truck = fleet.createTruck(TYPE_15_FOOT);
		truck.setRentable(true);
		store.addTruck(truck);

		assertThat(store.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(1)));
	}
}
