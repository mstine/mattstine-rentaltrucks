package com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.events.EventLog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class Reservations {

    private final EventLog eventLog;
    private final Set<Store> stores = new HashSet<>();
    private Set<Reservation> reservations = new HashSet<>();

    public Reservations(EventLog eventLog) {
        this.eventLog = eventLog;

        this.eventLog.subscribe("stores", event -> this.stores.add(new Store()));
    }

    public void add(Reservation reservation) {
        reservations.add(reservation);
    }

    public int size() {
        return reservations.size();
    }

    public Set<Store> findAllStores() {
        return Collections.unmodifiableSet(stores);
    }
}
