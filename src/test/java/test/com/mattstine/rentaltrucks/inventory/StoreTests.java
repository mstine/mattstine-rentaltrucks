package test.com.mattstine.rentaltrucks.inventory;

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

	@Before
	public void setUp() {
		store = new Store();
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotManageTruckType() {
		store.trucksOnHand(TYPE_15_FOOT);
	}

	@Test
	public void canAddATruckToInventory() {
		store.addTruck(new Truck(TYPE_15_FOOT, true, store));
		assertThat(store.trucksOnHand(TYPE_15_FOOT), is(equalTo(1)));
	}

	@Test
	public void canRemoveATruckFromInventory() {
		Truck truck = new Truck(TYPE_15_FOOT, true, store);
		store.addTruck(truck);
		store.removeTruck(truck);
		assertThat(store.trucksOnHand(TYPE_15_FOOT), is(equalTo(0)));
	}

}
