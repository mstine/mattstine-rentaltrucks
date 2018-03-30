package test.com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.events.EventLog;
import com.mattstine.rentaltrucks.fleet.StoreAddedEvent;
import com.mattstine.rentaltrucks.reservations.CatalogItem;
import com.mattstine.rentaltrucks.reservations.CatalogItemAddedEvent;
import com.mattstine.rentaltrucks.reservations.Reservation;
import com.mattstine.rentaltrucks.reservations.Reservations;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class ReservationsTests {

	private final int STORE_BOULDER = 0;
	private EventLog eventStore;
	private Reservations reservations;

	@Before
	public void setUp() throws Exception {
		eventStore = new EventLog();
		reservations = new Reservations(eventStore);
	}

	@Test
	public void canCreateReservation() {
		reservations.add(new Reservation(STORE_BOULDER,
				new CatalogItem("15 Foot Truck"),
				LocalDateTime.of(2018, Month.MARCH, 1, 10, 00),
				LocalDateTime.of(2018, Month.MARCH, 2, 10, 00)));

		assertThat(reservations.size(), is(equalTo(1)));
	}


	@Test
	public void addsStoreRecordOnStoreAddedEvent() {
		eventStore.publish("stores", new StoreAddedEvent());

		assertThat(reservations.findAllStores().size(), is(equalTo(1)));
	}

	@Test
	public void doesNotAddStoreOnOtherEvent() {
		eventStore.publish("catalog", new CatalogItemAddedEvent());

		assertThat(reservations.findAllStores().size(), is(equalTo(0)));
	}

}
