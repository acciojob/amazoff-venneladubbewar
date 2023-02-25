package com.driver;

import java.util.HashMap;

public class Repository {

    HashMap<String,Order> orderDB = new HashMap<>();

    public void addOrder(Order order)
    {
        orderDB.put(order.getId(),order);
    }
}
