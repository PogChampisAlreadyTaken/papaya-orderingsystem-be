package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.Reservation;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservation")
public class Reservationsystem {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/tables")
    public Response postTable(RestaurantTable[] tables) {
        for (int i = 0; i<tables.length; i++) {
            tables[i].persist();
        }
        return Response.ok().entity(tables).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postReservation(Reservation reservation) {
        reservation.persist();
        return Response.ok().entity(reservation).build();
    }
}
