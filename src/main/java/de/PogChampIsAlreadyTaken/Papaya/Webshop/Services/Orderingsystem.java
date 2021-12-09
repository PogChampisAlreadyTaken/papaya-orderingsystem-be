package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Address;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Orderingsystem {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello Orderingsystem, QwQ";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id")long id){
        Address address = Address.findById(id);
        return Response.ok().entity(address).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postAddress(Address address){
        address.persist();
        return Response.ok().entity(address).build();
    }
}