package com.acme.rantotta.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.validator.constraints.NotEmpty;

public class Order {

    @NotEmpty
    private String orderId;
    private Map<String, OrderItem> orderItemMap = new TreeMap<String, OrderItem>();
    private boolean delivered = false;
    
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public List<OrderItem> getOrderItemsFromOrder(){
        return new ArrayList<OrderItem>(orderItemMap.values());
    }
    public void addOrderItemToOrder(OrderItem orderItem){
        if(!delivered){
            orderItemMap.put(orderItem.getId(), orderItem);
        } else {
            throw new IllegalStateException("this order is delivered, cannot be modified");
        }
    }
    public boolean isDelivered(){
        return delivered;
    }
    public void setDelivered(){
        delivered = true;
    }
}
