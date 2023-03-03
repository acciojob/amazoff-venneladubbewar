package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderRepository {
    //orderid,order
    HashMap<String,Order> ordersDB = new HashMap<String, Order>();
    List<String> list = new ArrayList<>();

    //partnerid,partner
    HashMap<String,DeliveryPartner> partnersDB = new HashMap<>();

    List<String> al;
    List<String> list2 = new ArrayList<>();

    // partnerid, list of orderIds
    HashMap<String, List<String>> hm3 = new HashMap<String, List<String>>();


    public void addOrder(Order order) {
        ordersDB.put(order.getId(), order);
        list.add(order.getId());
    }

    public void addPartner(String partnerId) {
        partnersDB.put(partnerId,new DeliveryPartner(partnerId));

    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        if(ordersDB.containsKey(orderId)&& partnersDB.containsKey(partnerId)){
              if(hm3.containsKey(partnerId)) {
               al=hm3.get(partnerId);
               al.add(orderId);
               list2.add(orderId);
               hm3.put(partnerId,al);
              }
              else {
                  al = new ArrayList<>();
                  al.add(orderId);
                  list2.add(orderId);
                  hm3.put(partnerId,al);
              }
        }
    }

    public Order getOrderById(String orderId) {
       return ordersDB.get(orderId);
    }

    public  DeliveryPartner getPartnerById(String partnerId) {
        return partnersDB.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        List <String> all;
        all = (hm3.get(partnerId));
        int x= all.size();
        return x;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List <String> aal;
        aal = (hm3.get(partnerId));
        return aal;
    }

    public List<String> getAllOrders(){
        return list;
    }

    public int getCountOfUnassignedOrders() {
       return  list.size() - list2.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String timee, String  partnerId)   {

        String DeliveryTime [] = timee.split(":");
        int time = Integer.parseInt(DeliveryTime[0]) *60 +Integer.parseInt(DeliveryTime[1]);

        List <String> a;
        int count =0;

        a=hm3.get(partnerId);
        for(String x : a) {
            Order order = ordersDB.get(x);
            int t = order.getDeliveryTime();
            if (t < time) {
                count++;
            }
        }
            return count;
    }

   public String getLastDeliveryTimeByPartnerId(String partnerId) {
       List<String > al;

       al = hm3.get(partnerId);

       int ans =0;
       for(String x : al) {
           Order order = ordersDB.get(x);
           int t = order.getDeliveryTime();
           ans= Math.max(t,ans);
       }

    int time = ans/60;
       String hr = Integer.toString(time);
       String mins= "";
       if(hr.length()==1) {
           hr= "0" +  hr;
         ans = ans%60;
          mins = Integer.toString(ans);
         if(mins.length()==1) {
             mins = "0" + mins;
         }

       }
       return hr + ":" + mins ;
   }

   public void deletePartnerById(String partnerId) {
       List<String > al= new ArrayList<>();

        if(hm3.containsKey(partnerId)) {
            al = hm3.get(partnerId);
            hm3.remove(partnerId);
            partnersDB.remove(partnerId);

            for (String x : al) {
                if (list2.contains(x)) {
                    list2.remove(x);
                }
            }
        }
        else {
            partnersDB.remove(partnerId);
        }

   }

   public void deleteOrderById(String orderId) {

        if(list2.contains(orderId)) {
          //  String s = hm3.get(orderId).toString();
            list2.remove(orderId);
            hm3.remove(orderId);
            ordersDB.remove(orderId);

        }

        else {
            ordersDB.remove(orderId);
        }
   }

}