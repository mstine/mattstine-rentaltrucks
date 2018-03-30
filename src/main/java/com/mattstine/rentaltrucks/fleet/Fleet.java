package com.mattstine.rentaltrucks.fleet;

import com.mattstine.rentaltrucks.events.EventLog;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

/**
 * @author Matt Stine
 */
public class Fleet {

	private EventLog eventLog;
	private Set<Truck> trucks = new HashSet<>();
	private Set<Store> stores = new HashSet<>();

	public Fleet(EventLog eventLog) {
		this.eventLog = eventLog;
	}

	public int trucksOnHand() {
		return trucks.size();
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
		return store.findRentableTrucks(typeId);
	}

	public Truck createTruck(int typeId) {
		Truck truck = new Truck(typeId, false);
		trucks.add(truck);
		return truck;
	}

	public void assignTruck(Truck truck, Store store) {
		store.addTruck(truck);
	}

	public Store createStore() {
		Store store = new Store(UUID.randomUUID());
		stores.add(store);
		eventLog.publish("hello", new StoreAddedEvent());
		return store;
	}
}
