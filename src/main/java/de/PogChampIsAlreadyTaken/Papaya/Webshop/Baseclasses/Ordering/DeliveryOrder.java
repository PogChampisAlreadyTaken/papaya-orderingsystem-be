package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class DeliveryOrder extends PanacheEntity {

    private String customer;
    private String shoppingitem;

    public static List<DeliveryOrder> findByOrderbyCustomer(String customer) {
        return list("customer", customer);
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShoppingitem() {
        return shoppingitem;
    }

    public void setShoppingitem(String shoppingItem) {
        this.shoppingitem = shoppingItem;
    }
}
