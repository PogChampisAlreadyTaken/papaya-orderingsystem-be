package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Reservation extends PanacheEntity {

    public int tableid;
    public String name;
    public int phonenumber;
    public Date reservationdate;
    public Time reservationtime;
}
