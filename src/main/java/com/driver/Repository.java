package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Repository {

    HashMap<String,Order> orderDB = new HashMap<>();
    HashMap<String,DeliveryPartner> partnerIdDB = new HashMap<>();

    HashMap<String, List<String>> partnerOrders= new HashMap<String, List<String>>();

    List<String> allOrders100 = new ArrayList<>();

    HashMap<String,String> assignedOrders= new HashMap<>();


    public void addOrder(Order order)
    {
        orderDB.put(order.getId(),order);
        allOrders100.add(order.getId());
    }

    public void addPartner(String id)
    {
        partnerIdDB.put(id,new DeliveryPartner(id));
    }

    public void addOrderPartnerPair(String orderId,String partnerId)
    {
        assignedOrders.put(orderId,partnerId);
        if(orderDB.containsKey(orderId) && partnerIdDB.containsKey(partnerId))
        {
            List<String> a;
            if (partnerOrders.containsKey(partnerId)) {
                a = partnerOrders.get(partnerId);

            }
            else
            {
                a = new ArrayList<>();
            }
            a.add(orderId);
            partnerOrders.put(partnerId, a);
            DeliveryPartner temp= partnerIdDB.get(partnerId);
            temp.setNumberOfOrders(a.size());
        }


    }

    public Order IdtoOrder(String orderId)
    {
        return orderDB.get(orderId);
    }

    public DeliveryPartner PidToPartner(String partnerId)
    {

        DeliveryPartner temp= partnerIdDB.get(partnerId);
        return temp;
    }
    public int partnerOrders(String partnerId)
    {
        List<String> a;
        a=partnerOrders.get(partnerId);
        return a.size();
    }

    public List<String> partnerOrdersList(String partnerId)
    {
        List<String> a;
        a=partnerOrders.get(partnerId);
        return a;
    }
    public List<String> allOrdersList()
    {
        return allOrders100;
    }

    public int unassigneOrders()
    {
        return orderDB.size()-assignedOrders.size();
    }

    public int LateDeliveryR(int current, String partnerId)
    {
        List<String> a;
        int count=0;
        a=partnerOrders.get(partnerId);
       for(String temp : a)
       {
           Order o =orderDB.get(temp);
           int t= o.getDeliveryTime();
           if(t>current) count++;
       }
       return count;
    }

    public String lastDelivery(String partnerId)
    {
        List<String> a=new ArrayList<>();
        int ans=0;
        a=partnerOrders.get(partnerId);
        for(String s: a)
        {
            Order o = orderDB.get(s);
            int temp=o.getDeliveryTime();
            if(temp>ans) ans=temp;
        }


        int hours=ans/60;
        String Hours=Integer.toString(hours);
        if(Hours.length()==1) Hours= "0"+Hours;
        int min= ans%60;
        String Min = Integer.toString(min);
        if(Min.length()==1) Min= "0"+Min;

        return Hours+":"+Min;

//        if((ans/60)<10)
//        {
//            last+=(char)'0'+(char)(ans/60);
//        }
//        else last+=(char)ans/60;
//
//        last+=':';
//
//        if((ans%60)<10) last+=(char)'0'+(char)(ans%60);
//        else last+=(char)(ans%60);

        //return last;
    }

    public void removePartner(String partnerId)
    {
        List<String> a =new ArrayList<>();
        a=partnerOrders.get(partnerId);
        if(a.size()==0) partnerIdDB.remove(partnerId);
    }

    public void removeOrder(String orderId)
    {
        orderDB.remove(orderId);
    }

}
