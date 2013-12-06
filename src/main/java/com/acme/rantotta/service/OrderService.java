package com.acme.rantotta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.acme.rantotta.order.Order;

@Service
public class OrderService {

    private Map<String, Order> orderMap = new TreeMap<String, Order>();
    private Map<String, Integer> sessionIdMap = new HashMap<String, Integer>();
    private AtomicInteger cartId = new AtomicInteger(250);
    
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
    public Integer getCartIdBySessionId(String sessionId){
        if(!sessionIdMap.containsKey(sessionId)){
            sessionIdMap.put(sessionId, cartId.getAndIncrement());
        }
        return sessionIdMap.get(sessionId);
    }
    
}
