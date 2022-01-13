package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.Date;

/**
 * @author Franziska Hesselfeld
 */
@Entity
public class Reservation extends PanacheEntity {

    public int tableid;
    public long phonenumber;
    public String name;
    public Date reservationsdate;
}

