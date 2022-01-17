package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;

import java.util.List;

public class Orderparse {

    private long orderID;
    private String customer;
    private List<ShoppingItemParse> shoppingItem;

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<ShoppingItemParse> getShoppingItem() {
        return shoppingItem;
    }

    public void setShoppingItemList(List<ShoppingItemParse> shoppingItem) {
        this.shoppingItem = shoppingItem;
    }
}
