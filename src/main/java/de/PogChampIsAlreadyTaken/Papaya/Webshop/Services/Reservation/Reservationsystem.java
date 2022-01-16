package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.Reservation;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Franziska Hesselfeld
 */
@Path("/reservation")
public class Reservationsystem {
    private static final Logger LOG = Logger.getLogger(Reservationsystem.class);

    @POST
    @Transactional
    @Path("/{time}/{name}/{tableid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postReservation(@PathParam("time") Long time,
                                    @PathParam("name") String name,
                                    @PathParam("tableid") int tableid,
                                    String phonenumber) {
        LOG.info("POST new Reservation for table: "+tableid);

        //check input before processing
        if(name.isEmpty() || name.equals(" ")){
            return Response.status(470).entity("Name is empty").build();
        } else if(tableid == 0) {
            return Response.status(471).entity("TableId is empty").build();
        }

        //tableid is not in the database
        Date date = new Date(time);

        Reservation newReservation = new Reservation();
        newReservation.reservation_timestamp = new Timestamp(time);
        newReservation.reservation_date = date;
        newReservation.tableid = tableid;
        newReservation.phonenumber = Long.parseLong(phonenumber);
        newReservation.name = name;

        //check if Reservation already exist
        List<Reservation> allReservations = Reservation.findAllReservationOnSpecificDay(newReservation.reservation_date);
        for(Reservation reservation: allReservations){
            if(newReservation.equals(reservation)){
                LOG.error("Reservation already exist: "+reservation.toString());
                return Response.status(409).entity("Reservation already exist").build();
            }
        }

        newReservation.persist();
        LOG.info("Successful created Reservation for table: "+tableid);
        return Response.ok().entity(newReservation.toString()).build();
    }




    @Path("/table/{datetime}/{seats}")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFreeTable(@PathParam("datetime") long dateTime, @PathParam("seats") int seats){
        LOG.info("GET free table on: "+dateTime+" for "+seats+" people");

        //first, check input
        if(seats == 0){
            return Response.status(463).entity("Personenanzahl darf nicht 0 sein!").build();
        } else if (seats > 8){
            return Response.status(464).entity("Personenanzahl darf nicht größer als 8 sein!").build();
        }


        List<RestaurantTable> freeTables = Reservation.getAllFreeTables(dateTime, seats);
        LOG.info("Free tables found: "+freeTables.toString());

        if(freeTables.isEmpty()){
            LOG.error("No free table was found on time: "+dateTime);
            return Response.status(465)
                    .entity("No free table was found. Try another time. Current time: "+dateTime)
                    .build();
        }

        return Response.ok().entity(freeTables.get(0).id).build();
    }
}
