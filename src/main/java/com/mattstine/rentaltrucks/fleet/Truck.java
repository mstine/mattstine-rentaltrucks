package com.mattstine.rentaltrucks.fleet;

/**
 * @author Matt Stine
 */
public class Truck {

	private final int typeId;
	private boolean rentable;

	Truck(int typeId, boolean rentable) {
		this.typeId = typeId;
		this.rentable = rentable;
	}

	public boolean isRentable() {
		return rentable;
	}

	public void setRentable(boolean rentable) {
		this.rentable = rentable;
	}

	public int getTypeId() {
		return typeId;
	}
}
