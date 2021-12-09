package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Address;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Orderingsystem {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        String hello = "Hello Orderingsystem, QwQ";
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(hello);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") long id) {
        Address address = Address.findById(id);
        return Response.ok().entity(address).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postAddress(Address address) {
        address.persist();
        return Response.ok().entity(address).build();
    }
}