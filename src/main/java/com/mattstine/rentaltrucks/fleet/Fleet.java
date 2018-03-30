package com.mattstine.rentaltrucks.fleet;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author Matt Stine
 */
public class Fleet {

	private Set<Truck> trucks = new HashSet<>();
	private Set<Store> stores = new HashSet<>();

	public void addTruck(Truck truck) {
		trucks.add(truck);
	}

	public int trucksOnHand() {
		return trucks.size();
	}

	public void addStore(Store store) {
		stores.add(store);
	}

	public int storesOnHand() {
		return stores.size();
	}

	public Set<Truck> findRentableTrucks(int typeId) {
		return trucks.stream()
				.filter(t -> t.getTypeId() == typeId)
				.filter(Truck::isRentable)
				.collect(toSet());
	}

	public Set<Truck> findRentableTrucks(int typeId, Store store) {
		return findRentableTrucks(typeId).stream()
				.filter(t -> t.getStore() == store)
				.collect(toSet());
	}
}
