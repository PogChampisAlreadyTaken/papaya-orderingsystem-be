package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Franziska Hesselfeld
 */
@Entity
public class RestaurantTable extends PanacheEntityBase {
    @Id
    public long id;
    public int seats;

    public static List<RestaurantTable> findAllRestaurantTablesWithEnoughSpace(int seats){
        List<RestaurantTable> restaurantTablesWithEnoughSpace = RestaurantTable.findAll().list();
        return restaurantTablesWithEnoughSpace
                .stream()
                .filter(restaurantTable -> restaurantTable.seats >= seats)
                .collect(Collectors.toList());
    }
}
