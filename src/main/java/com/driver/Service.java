package com.driver;

public class Service {

    Repository repository= new Repository();

    public void addOrder(Order order)
    {
        repository.addOrder(order);
    }
}
