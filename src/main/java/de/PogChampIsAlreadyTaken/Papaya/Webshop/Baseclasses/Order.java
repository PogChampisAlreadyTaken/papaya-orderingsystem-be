package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;

public class Order {

    private LinkedList<Item> itemLinkedList;
    private Long sum;
    private Customer customer;
    private Date date;
    private Time time;
}
