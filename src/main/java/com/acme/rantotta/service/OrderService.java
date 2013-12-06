package com.acme.rantotta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.acme.rantotta.order.Order;

@Service
public class OrderService {

    private Map<String, Order> orderMap = new TreeMap<String, Order>();
    
    public void addOrder(Order order){
        orderMap.put(order.getOrderId(), order);
    }
    public void deleteById(String id){
        orderMap.remove(id);
    }
    public Order findById(String id){
        return orderMap.get(id);
    }
    public List<Order> getAll(){
        return new ArrayList<Order>(orderMap.values());
    }
}
