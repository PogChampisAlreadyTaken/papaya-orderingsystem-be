package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Franziska Hesselfeld
 */
@Entity
public class RestaurantTable extends PanacheEntityBase {
    @Id
    public long id;
    public int seats;
}
