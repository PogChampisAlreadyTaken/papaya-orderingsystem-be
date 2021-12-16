package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.Reservation;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("/reservation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Reservationsystem {

    @POST
    @Transactional
    @Path("/tables")
    public Response postTable(RestaurantTable[] tables) {
        for (int i = 0; i < tables.length; i++) {
            tables[i].persist();
        }
        return Response.ok().entity(tables).build();
    }

    @POST
    @Transactional
    public Response postReservation(Reservation reservation) {
        //buissness logic
        //only persist, if
        /*
        Reservierung:

Tisch ist für 2 h reserviert.

Möchte man reservieren, so gibt man den Start-Zeitpunkt ein.

Die Startzeit wird genommen und es wird überprüft:

Gibt es bereits den Tisch schon mit dieser Startzeit?
Liegt der Tisch in einer 2h-Sperre?
Wenn nicht --> Reservierung erfolgreich
Sonst: Fehlerfall --> Tisch kann nicht reserviert werden, andere Zeit wählen
2 unterschiedliche Restcalls:
POST: CheckAvailability
POSt: Reservation
         */
        reservation.persist();
        return Response.ok().entity(reservation).build();
    }
    
    @GET
    public Response getFreeTables(Date reservationDateWanted, Time reservationTimeWanted){
        //get all free tables at a specific time
        // get all Reservation on this day:
        List<Reservation> reservatedTablesAtSpecificDay = Reservation.list("reservationdate", reservationDateWanted);
        List<Reservation> alreadyTaken = new ArrayList<Reservation>();

        //for checking free table
        LocalTime reservationTimeWantedLoc = reservationTimeWanted.toLocalTime();
        int duration = LocalTime.of(2,0).getHour();
        LocalTime reservationTimeWantedMin = reservationTimeWantedLoc.plusHours(duration);
        LocalTime reservationTimeWantedMax = reservationTimeWantedLoc.minusHours(duration);

        //check if a table is reservated at the time
        reservatedTablesAtSpecificDay.forEach(reservation -> {
            if(reservationTimeWantedLoc.compareTo(reservationTimeWanted.toLocalTime()) == 0){
                alreadyTaken.add(reservation);
            }
            if(reservation.reservationtime.toLocalTime().compareTo(reservationTimeWantedMax) < 0 &&
                reservation.reservationtime.toLocalTime().compareTo(reservationTimeWantedMin) > 0){
                //table is already tanken
                alreadyTaken.add(reservation);
            }
        });

        List<RestaurantTable> restaurantTable = RestaurantTable.listAll();
        

        //find all reservations, which will be in the futuret
        List<RestaurantTable> freeTables = new ArrayList<>();
        return Response.ok().entity(freeTables).build();
    }



    //Delete a table
    @DELETE
    @Transactional
    @Path("/tables/{id}")
    public Response deleteTable(@PathParam("id") long id) {

        RestaurantTable table = RestaurantTable.findById(id);
        if (table == null)
            return Response.status(404).build();

        table.delete();
        return Response.ok().entity(table).build();
    }


}
