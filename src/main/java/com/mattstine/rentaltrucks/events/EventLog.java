package com.mattstine.rentaltrucks.events;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class EventLog {
	private Set<EventHandler> subscribers = new HashSet<>();

	public void subscribe(EventHandler handler) {
		this.subscribers.add(handler);
	}

	public void publish(Event event) {
		subscribers.stream()
				.forEach(subscriber -> subscriber.handleEvent(event));
	}

	public int getNumberOfSubscribers() {
		return subscribers.size();
	}
}
