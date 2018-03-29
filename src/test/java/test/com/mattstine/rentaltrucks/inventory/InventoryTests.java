package test.com.mattstine.rentaltrucks.inventory;

import com.mattstine.rentaltrucks.inventory.Inventory;
import com.mattstine.rentaltrucks.inventory.Store;
import com.mattstine.rentaltrucks.inventory.Truck;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Matt Stine
 */
public class InventoryTests {
	private static final int TYPE_15_FOOT = 0;
	private static final int TYPE_24_FOOT = 1;
	private Inventory inventory;
	private Store store;

	// Inventory keeps track of all the trucks we have at the company

	@Before
	public void setUp() throws Exception {
		inventory = new Inventory();
		store = new Store();
	}

	@Test
	public void canAddTruckToInventory() {
		inventory.addTruck(new Truck(TYPE_15_FOOT, false, store));

		assertThat(inventory.trucksOnHand(), is(equalTo(1)));
	}

	@Test
	public void canAddStoreToInventory() {
		inventory.addStore(new Store());

		assertThat(inventory.storesOnHand(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksByType() {
		inventory.addTruck(new Truck(TYPE_24_FOOT, true, store));

		assertThat(inventory.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(0)));

		inventory.addTruck(new Truck(TYPE_15_FOOT,true, store));

		assertThat(inventory.findRentableTrucks(TYPE_15_FOOT).size(), is(equalTo(1)));
	}

	@Test
	public void findRentableTrucksAtStore() {
		inventory.addTruck(new Truck(TYPE_24_FOOT, true, store));

		assertThat(inventory.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(0)));

		inventory.addTruck(new Truck(TYPE_15_FOOT, true, store));
		inventory.addTruck(new Truck(TYPE_15_FOOT, false, store));
		inventory.addTruck(new Truck(TYPE_15_FOOT, true, null));

		assertThat(inventory.findRentableTrucks(TYPE_15_FOOT, store).size(), is(equalTo(1)));
	}

}
