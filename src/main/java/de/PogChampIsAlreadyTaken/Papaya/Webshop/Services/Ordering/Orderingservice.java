package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services.Ordering;

import com.google.gson.Gson;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering.DeliveryOrder;
import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering.Orderparse;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

@Path("/order")
public class Orderingservice {

    @GET
    public String hello() {
        String hello = "Hello Orderingsystem, QwQ";
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(hello);
    }

    @GET
    @Path("/{customer}")
    public Response getAddress(@PathParam("customer") String customer) {
        Gson gson = new Gson();
        List<Orderparse> orderparseList = new LinkedList<>();
        List<DeliveryOrder> deliveryOrders = DeliveryOrder.findByOrderbyCustomer(customer);
        deliveryOrders.forEach((order) -> {
            Orderparse orderparse = new Orderparse();
            orderparse.setOrderID(order.id);
            orderparse.setCustomer(order.getCustomer());
            orderparse.setShoppingItemList(gson.fromJson(order.getShoppingitem(), LinkedList.class));
            orderparseList.add(orderparse);
        });
        return Response.ok().entity(orderparseList).build();
    }

    @POST
    @Transactional
    public Response postOrder(String order) {
        Gson gson = new Gson();
        Orderparse orderparse = gson.fromJson(order, Orderparse.class);

        DeliveryOrder deliveryOrder = new DeliveryOrder();

        deliveryOrder.setCustomer(orderparse.getCustomer());

        deliveryOrder.setShoppingitem(gson.toJson(orderparse.getShoppingItem()));

        deliveryOrder.persist();

        return Response.ok().entity(order).build();
    }
}