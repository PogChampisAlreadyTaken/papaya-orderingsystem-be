package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class RestaurantTable extends PanacheEntity {

    public int seats;
}
