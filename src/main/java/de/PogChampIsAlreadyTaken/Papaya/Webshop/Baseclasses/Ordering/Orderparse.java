package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;


import java.util.List;

public class Orderparse {

    private long orderID;
    private String customer;
    private List<ShoppingItem> shoppingItem;

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

    public List<ShoppingItem> getShoppingItem() {
        return shoppingItem;
    }

    public void setShoppingItemList(List<ShoppingItem> shoppingItem) {
        this.shoppingItem = shoppingItem;
    }
}
