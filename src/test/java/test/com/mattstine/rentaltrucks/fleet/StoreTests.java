package test.com.mattstine.rentaltrucks.fleet;

import com.mattstine.rentaltrucks.events.EventLog;
import com.mattstine.rentaltrucks.fleet.Fleet;
import com.mattstine.rentaltrucks.fleet.Store;
import com.mattstine.rentaltrucks.fleet.Truck;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Matt Stine
 */
public class StoreTests {

    private static final int TYPE_15_FOOT = 0;

    private Store store;
    private Fleet fleet;

    @Before
    public void setUp() {
        EventLog eventLog = new EventLog();
        fleet = new Fleet(eventLog);
        store = fleet.createStore();
    }

    @Test
    public void cannotManageTruckType() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> store.trucksOnHand(TYPE_15_FOOT));
    }

    @Test
    public void canAddATruckToInventory() {
        store.addTruck(fleet.createTruck(TYPE_15_FOOT));
        assertThat(store.trucksOnHand(TYPE_15_FOOT)).isEqualTo(1);
    }

    @Test
    public void canRemoveATruckFromInventory() {
        Truck truck = fleet.createTruck(TYPE_15_FOOT);
        store.addTruck(truck);
        store.removeTruck(truck);
        assertThat(store.trucksOnHand(TYPE_15_FOOT)).isEqualTo(0);
    }

    @Test
    public void doesStoreHaveTruck() {
        Truck truck = fleet.createTruck(TYPE_15_FOOT);
        store.addTruck(truck);

        assertThat(store.hasTruck(truck)).isTrue();
    }

    @Test
    public void findTrucks() {
        Truck truck = fleet.createTruck(TYPE_15_FOOT);
        store.addTruck(truck);

        assertThat(store.findTrucks(TYPE_15_FOOT)).hasSize(1);
    }
}
