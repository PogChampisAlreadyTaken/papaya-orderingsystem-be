package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses;

import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Address;

import java.util.UUID;

public class Customer {

    private String name;
    private Address address;
    private final UUID customerID;

    public Customer(String name, Address address, UUID customerID) {
        this.name = name;
        this.address = address;
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UUID getCustomerID() {
        return customerID;
    }
}
