package com.mattstine.rentaltrucks.fleet;

import java.util.*;

/**
 * @author Matt Stine
 */
public class Store {

    private UUID id;
    private Map<Integer, Set<Truck>> trucksByType = new HashMap<>();

    Store(UUID id) {
        this.id = id;
    }

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

    public Set<Truck> findTrucks(int typeId) {
        return Collections.unmodifiableSet(trucksByType.computeIfAbsent(typeId, k -> new HashSet<>()));
    }

    public UUID getId() {
        return id;
    }
}
