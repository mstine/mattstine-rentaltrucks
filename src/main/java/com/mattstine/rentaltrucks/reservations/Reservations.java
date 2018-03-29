package com.mattstine.rentaltrucks.reservations;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class Reservations {

	private Set<Reservation> reservations = new HashSet<>();

	public void add(Reservation reservation) {
		reservations.add(reservation);
	}

	public int size() {
		return reservations.size();
	}
}
