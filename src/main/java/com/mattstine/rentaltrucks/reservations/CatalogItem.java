package com.mattstine.rentaltrucks.reservations;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author Matt Stine
 */
@Table("catalog_item")
public class CatalogItem {

    @PrimaryKey
    private final String id;

    @Column("truck_type")
    private final String truckType;

    public CatalogItem(String id, String truckType) {
        this.id = id;
        this.truckType = truckType;
    }

    public String getId() {
        return id;
    }

    public String getTruckType() {
        return truckType;
    }
}
