package com.driver;

import java.util.List;

public class Service {

    Repository repository= new Repository();

    public void addOrder(Order order)
    {
        repository.addOrder(order);
    }

    public void addPartner(String partnerId)
    {
        repository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId)
    {
        repository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order IdtoOrder(String orderId)
    {
       return  repository.IdtoOrder(orderId);
    }

    public DeliveryPartner PidToPartner(String partnerId)
    {
        return repository.PidToPartner(partnerId);
    }

    public int partnerOrders(String partnerId)
    {
        return repository.partnerOrders(partnerId);
    }

    public List<String> partnerOrdersList(String partnerId)
    {
        return repository.partnerOrdersList(partnerId);
    }

    public List<String> allOrdersList()
    {
        return repository.allOrdersList();
    }

    public int unassigneOrders()
    {
        return repository.unassigneOrders();
    }

    public int LateDeliveryS(int current,String partnerId)
    {
        return repository.LateDeliveryR(current,partnerId);
    }
    public String lastDelivery(String partnerId)
    {
        return repository.lastDelivery(partnerId);
    }
    public void removePartner(String partnerId)
    {
        repository.removePartner(partnerId);
    }
    public void removeOrder(String orderId)
    {
        repository.removeOrder(orderId);
    }
}
