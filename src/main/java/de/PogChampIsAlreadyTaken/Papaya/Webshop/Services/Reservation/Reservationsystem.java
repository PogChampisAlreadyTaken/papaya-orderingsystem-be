package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.Reservation;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * @author Franziska Hesselfeld
 */
@Path("/reservation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Reservationsystem {
    private static final Logger LOG = Logger.getLogger(Reservationsystem.class);

    @POST
    @Transactional
    @Path("/tables")
    public Response postTable(RestaurantTable[] tables) {
        LOG.info("POSt new Tables: "+Arrays.toString(tables));

        if(tables == null){
            LOG.error("There is no table to be inserted.");
            return Response.status(400).entity("There is no table to be inserted in this request").build();
        }
        for (RestaurantTable table : tables) {
            //check if table to be inserted already exist
            if(RestaurantTable.findById(table.id) == null){
                LOG.error("Table already exist. Table: "+table.toString());
                return Response.status(409).entity("Table already exist. Table: "+table.toString()).build();
            }
            table.persist();
        }

        LOG.info("Tables successful created. New tables: "+Arrays.toString(tables));
        return Response.ok().status(201).build();
    }

    @POST
    @Transactional
    @Path("/{time}")
    public Response postReservation(@PathParam("time") Long time, Reservation reservation) {
        //übersetze long in time
        //reservation.persist();
        return Response.ok().entity(reservation).build();
    }

    @Path("/table")
    @POST
    @Transactional
    public Response getFreeTable(long dateTime){
        //get times, where tables are free mit einer Zeitspanne von einer halben Stunde und 15 Minuten-Takt zurück
        Date date = new Date(dateTime);

        // get all Reservation on this day:
        List<Reservation> reservatedTablesAtSpecificDay = Reservation.list("reservationdate", date);

        //get all free tables at a specific time
        // get all Reservation on this day:
        //List<Reservation> reservatedTablesAtSpecificDay = Reservation.list("reservationdate", date);
       // List<Reservation> alreadyTaken = new ArrayList<Reservation>();

        //for checking free table
        /*
        LocalDate reservationTimeWantedLoc = date.toLocalDate();
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
        */

        //find all reservations, which will be in the futuret
        //List<RestaurantTable> freeTables = new ArrayList<>();
        return Response.ok().entity(reservatedTablesAtSpecificDay).build();
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
