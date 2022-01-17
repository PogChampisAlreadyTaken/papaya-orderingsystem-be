package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.RowId;

import javax.persistence.*;

@Entity
public class Address extends PanacheEntity {

    public String street;
    public String houseNumber;
    public String city;
    public Integer zip;
}
