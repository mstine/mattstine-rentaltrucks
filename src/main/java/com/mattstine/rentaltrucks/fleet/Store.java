package com.mattstine.rentaltrucks.fleet;

import com.mattstine.rentaltrucks.reservations.Catalog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * @author Matt Stine
 */
public class Store {

	private Map<Integer, Set<Truck>> trucksByType = new HashMap<>();

	public int trucksOnHand(int typeId) {
		if (trucksByType.get(typeId) == null) {
			throw new IllegalArgumentException();
		}

		return trucksByType.get(typeId).size();
	}

	public void addTruck(Truck truck) {
		int typeId = truck.getTypeId();
		trucksByType.computeIfAbsent(typeId, k -> new HashSet<>());

		trucksByType
				.get(typeId)
				.add(truck);
	}

	public void removeTruck(Truck truck) {
		trucksByType
				.get(truck.getTypeId())
				.remove(truck);
	}

	public boolean hasTruck(Truck truck) {
		return trucksByType.get(truck.getTypeId()).contains(truck);
	}

	public Set<Truck> findRentableTrucks(int typeId) {
		trucksByType.computeIfAbsent(typeId, k -> new HashSet<>());

		return trucksByType.get(typeId).stream()
				.filter(Truck::isRentable)
				.collect(toSet());
	}
}
