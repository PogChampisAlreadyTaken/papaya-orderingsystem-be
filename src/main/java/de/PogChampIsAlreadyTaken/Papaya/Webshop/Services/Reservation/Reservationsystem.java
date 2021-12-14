package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.Reservation;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        reservation.persist();
        return Response.ok().entity(reservation).build();
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
