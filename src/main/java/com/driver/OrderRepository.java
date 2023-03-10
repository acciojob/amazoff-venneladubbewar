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

//    List<String> al;
    List<String> list2 = new ArrayList<>();

    // partnerid, list of orderIds
    HashMap<String, List<String>> hm3 = new HashMap<String, List<String>>();
//orderid, partnerid
    HashMap<String,String > hm4 = new HashMap<>();

    public void addOrder(Order order) {
        ordersDB.put(order.getId(), order);
        list.add(order.getId());
    }

    public void addPartner(String partnerId) {
        partnersDB.put(partnerId,new DeliveryPartner(partnerId));

    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        List<String> al;
        hm4.put(orderId,partnerId);
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
            partnersDB.get(partnerId).setNumberOfOrders(hm3.get(partnerId).size());
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
            if (t > time) {
                count++;
            }
        }
            return count;
    }
//    public int LateDeliveryR(int current, String partnerId)
//    {
//        List<String> a;
//        int count=0;
//        a=partnerOrders.get(partnerId);
//        for(String temp : a)
//        {
//            Order o =orderDB.get(temp);
//            int t= o.getDeliveryTime();
//            if(t>current) count++;
//        }
//        return count;
//    }


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
       //String mins= "";
       if(hr.length()==1) {
           hr = "0" + hr;
       }
         ans = ans%60;
          String mins = Integer.toString(ans);
         if(mins.length()==1) {
             mins = "0" + mins;
         }


       return hr + ":" + mins ;
//
//       int hours=ans/60;
//       String Hours=Integer.toString(hours);
//       if(Hours.length()==1) Hours= "0"+Hours;
//       int min= ans%60;
//       String Min = Integer.toString(min);
//       if(Min.length()==1) Min= "0"+Min;
//
//       return Hours+":"+Min;
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

       ordersDB.remove(orderId);
       if(hm4.containsKey(orderId))
       {
           hm4.remove(orderId);
           String partnerId=hm4.get(orderId);
           hm3.get(partnerId).remove(orderId);
           partnersDB.get(partnerId).setNumberOfOrders(hm3.get(partnerId).size());
       }
   }

}