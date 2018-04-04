package com.mattstine.rentaltrucks.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class EventLog {
    private Map<String, Set<EventHandler>> topics = new HashMap<>();

    public void subscribe(String topic, EventHandler handler) {
        Set<EventHandler> subscribers = this.topics.computeIfAbsent(topic, k -> new HashSet<>());
        subscribers.add(handler);
    }

    public void publish(String topic, Event event) {
        Set<EventHandler> subscribers = this.topics.computeIfAbsent(topic, k -> new HashSet<>());
        subscribers.stream()
                .forEach(subscriber -> subscriber.handleEvent(event));
    }

    public int getNumberOfSubscribers(String topic) {
        return topics.size();
    }
}
