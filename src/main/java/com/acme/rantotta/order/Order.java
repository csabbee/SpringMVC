package com.acme.rantotta.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.validator.constraints.NotEmpty;

import com.acme.rantotta.domain.Food;

public class Order {

    @NotEmpty
    private String orderId;
    private Map<String, Food> foodMap = new TreeMap<String, Food>();
    private boolean delivered = false;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public List<Food> getFoodsFromOrder(){
        return new ArrayList<Food>(foodMap.values());
    }
    public void addFoodToOrder(Food food){
        if(!delivered){
            foodMap.put(food.getId(), food);
        } else {
            throw new IllegalStateException("this order is delivered, food cannot be added to it");
        }
    }
    public boolean isDelivered(){
        return delivered;
    }
    public void setDelivered(){
        delivered = true;
    }
}
