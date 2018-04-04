package com.mattstine.rentaltrucks.fleet;

/**
 * @author Matt Stine
 */
public class Truck {

    private final int typeId;

    Truck(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
