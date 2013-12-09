package com.acme.rantotta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.rantotta.domain.Food;
import com.acme.rantotta.order.Order;

@Service
public class OrderService {

    private Map<Integer, Order> orderMap = new TreeMap<Integer, Order>();
    private Map<String, Integer> sessionIdMap = new HashMap<String, Integer>();
    private AtomicInteger cartId = new AtomicInteger(250);
    private FoodService foodService;
    
    @Autowired
    public OrderService(FoodService foodService) {
        super();
        this.foodService = foodService;
    }
    
    public void addOrder(Order order){
        orderMap.put(order.getOrderId(), order);
    }
    public void deleteById(String id){
        orderMap.remove(id);
    }
    public Order getOrderById(Integer cartId){
        if(sessionIdMap.containsValue(cartId)){
            if (!orderMap.containsKey(cartId)) {
                Order order = new Order();
                order.setOrderId(cartId);
                orderMap.put(cartId, order);
            }
        } else {
            throw new IllegalArgumentException("Cartid does not exist! You have to call POST /cart first");
        }
        return orderMap.get(cartId);
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
    public Food getFoodById(String foodId){
        return foodService.find(foodId);
    }
}
