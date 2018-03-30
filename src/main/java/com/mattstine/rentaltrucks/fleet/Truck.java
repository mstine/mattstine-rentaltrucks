package com.mattstine.rentaltrucks.fleet;

/**
 * @author Matt Stine
 */
public class Truck {

	private final int typeId;
	private final boolean rentable;
	private final Store store;

	public Truck(int typeId, boolean rentable, Store store) {
		this.typeId = typeId;
		this.rentable = rentable;
		this.store = store;
	}

	public int getTypeId() {
		return typeId;
	}

	public boolean isRentable() {
		return rentable;
	}

	public Store getStore() {
		return store;
	}
}
