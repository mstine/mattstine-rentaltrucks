package test.com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.reservations.CatalogItem;
import com.mattstine.rentaltrucks.reservations.Reservation;
import com.mattstine.rentaltrucks.reservations.Reservations;
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

	@Test
	public void canCreateReservation() {
		Reservations reservations = new Reservations();
		reservations.add(new Reservation(STORE_BOULDER,
				new CatalogItem("15 Foot Truck"),
				LocalDateTime.of(2018, Month.MARCH, 1, 10, 00),
				LocalDateTime.of(2018, Month.MARCH, 2, 10, 00)));

		assertThat(reservations.size(), is(equalTo(1)));
	}

	// Q1: List truck types by pickup date time (ignore Geo for now - we have one store)

	/*

	Do we offer a truck type

	 */

}
