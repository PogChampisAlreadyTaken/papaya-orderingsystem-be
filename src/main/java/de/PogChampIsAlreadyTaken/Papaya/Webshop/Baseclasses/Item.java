package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses;

import java.util.UUID;

public class Item {

    private String name;
    private String description;
    private final UUID itemID;
    private Long price;


    public Item(String name, String description, UUID itemID, Long price) {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getItemID() {
        return itemID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
