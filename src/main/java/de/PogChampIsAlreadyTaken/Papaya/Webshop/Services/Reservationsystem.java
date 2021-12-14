package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Address;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.RestaurantTable;

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
    public Response postTable(RestaurantTable table) {
        table.persist();
        return Response.ok().entity(table).build();
    }
}
