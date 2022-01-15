package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Franziska Hesselfeld
 */
@Entity
public class Reservation extends PanacheEntity {

    public int tableid;
    public long phonenumber;
    public String name;
    public Timestamp reservation_timestamp;
    public Date reservation_date;


    public static List<Reservation> findAllReservationOnSpecificDay(Date date){
        return list("reservation_date", date);
    }

    @Override
    public String toString(){
        return "Reservation: (tableId: "+ tableid+
                " phoneNumber: "+phonenumber+
                " name: "+name+
                " reservationDate: "+ reservation_timestamp +")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }

        Reservation other = (Reservation) o;

        return this.toString().equals(other.toString());
    }


    public static List<Reservation> getSpecificTablesinDuration(List<Reservation> reservationOnSpecificDay, long reservationTime){

        Timestamp finalTime = new Timestamp(reservationTime+(60*60*1000));
        Timestamp beginTime = new Timestamp(reservationTime-(60*60*1000));

        reservationOnSpecificDay =  reservationOnSpecificDay
                .stream()
                .filter(r ->
                        (beginTime.before(r.reservation_timestamp)  &&  finalTime.after(r.reservation_timestamp))
                                || (beginTime.equals(r.reservation_timestamp) || finalTime.equals(r.reservation_timestamp)))
                .collect(Collectors.toList());

        return reservationOnSpecificDay;
    }

    public static List<RestaurantTable> getAllFreeTablesAfterReservationCheck(List<Reservation> filterdReservations,
                                                                              List<RestaurantTable> tables){

        //checken ob passende Tische in der reservations list liegen --> denn danach kann ich dann dne Tisch auswählen und zurück schicken

        for(RestaurantTable table: tables){
            for(Reservation reservation: filterdReservations){
                if(table.id == reservation.id){
                    //delete it from tables
                    tables.remove(table);
                }
            }
        }
        return tables;
    }

    public static List<RestaurantTable> getAllFreeTables(long dateTime, int seats){
        //get all tables with the right amount of seats
        List<RestaurantTable> restaurantTablesWithEnoughSpace =
                RestaurantTable.findAllRestaurantTablesWithEnoughSpace(seats);

        Date dateTimeOfReservation = new Date(dateTime);

        //get all Reservation on this day:
        List<Reservation> reservatedTablesAtSpecificDay;
        reservatedTablesAtSpecificDay = Reservation.findAllReservationOnSpecificDay(dateTimeOfReservation);


        List<Reservation> filterdReservations;
        filterdReservations = Reservation.getSpecificTablesinDuration(reservatedTablesAtSpecificDay, dateTime);

        //jetzt hab ich alle Reservierungen, die in der Zeitspanne liegen
        restaurantTablesWithEnoughSpace = Reservation.getAllFreeTablesAfterReservationCheck(filterdReservations,
                restaurantTablesWithEnoughSpace);

        return restaurantTablesWithEnoughSpace;

    }
}

