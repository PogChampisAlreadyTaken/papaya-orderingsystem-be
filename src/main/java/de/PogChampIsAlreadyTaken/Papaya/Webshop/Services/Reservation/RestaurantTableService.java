package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Reservation;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Reservation.RestaurantTable;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/tables")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantTableService {
    private static final Logger LOG = Logger.getLogger(RestaurantTableService.class);

    @POST
    @Transactional
    public Response postTables(RestaurantTable[] tables) {
        LOG.info("POSt new Tables: "+ Arrays.toString(tables));

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


    //Delete a table
    @DELETE
    @Transactional
    @Path("/tables/{id}")
    public Response deleteTable(@PathParam("id") long id) {
        LOG.info("Delete table with id: "+id);

        RestaurantTable table = RestaurantTable.findById(id);
        if (table == null){
            LOG.error("Table with id "+id+" was not found"+id);
            return Response.status(404).build();

        }

        table.delete();
        LOG.error("Table with id "+id+" was deleted"+id);
        return Response.ok().entity(table).build();
    }

}
