package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        String s="";
        s+=deliveryTime.charAt(0);
        s+=deliveryTime.charAt(1);
        int h=Integer.parseInt(s);
        String s2="";
        s2+=deliveryTime.charAt(3);
        s2+=deliveryTime.charAt(4);
        int m=Integer.parseInt(s2);
        this.deliveryTime=(h*60)+m;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
