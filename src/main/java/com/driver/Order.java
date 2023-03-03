package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String DeliveryTime [] = deliveryTime.split(":");
        int time = Integer.parseInt(DeliveryTime[0]) *60 +Integer.parseInt(DeliveryTime[1]);

        this.deliveryTime=time;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
