package com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.events.EventLog;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class Catalog {

	private final EventLog eventLog;
	private Set<CatalogItem> items = new HashSet<>();

	public Catalog(EventLog eventLog) {
		this.eventLog = eventLog;
	}

	public void add(CatalogItem item) {
		this.items.add(item);
		eventLog.publish(new CatalogItemAddedEvent());
	}

	public int size() {
		return this.items.size();
	}

	public Set<CatalogItem> findAll() {
		return Collections.unmodifiableSet(items);
	}
}
