package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Reservation extends PanacheEntity {

    public int tableid;
    public String name;
    public int phonenumber;
    public Date time;
}
