package com.acme.rantotta.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.validator.constraints.NotEmpty;

import com.acme.rantotta.exception.OrderServiceException;

public class Order {

    @NotEmpty
    private Integer orderId;
    private Map<String, OrderItem> orderItemMap = new TreeMap<String, OrderItem>();
    private boolean delivered = false;
    
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public List<OrderItem> getOrderItemsFromOrder(){
        return new ArrayList<OrderItem>(orderItemMap.values());
    }
    public void addOrderItemToOrder(OrderItem orderItem){
        if(!delivered){
            if(!orderItemMap.containsKey(orderItem.getId())){
                orderItemMap.put(orderItem.getId(), orderItem);
            } else {
                OrderItem currentItem = orderItemMap.get(orderItem.getId());
                currentItem.setQuantity(currentItem.getQuantity()+orderItem.getQuantity());
            }
        } else {
            throw new OrderServiceException("this order is delivered, cannot be modified");
        }
    }
    public boolean isDelivered(){
        return delivered;
    }
    public void setDelivered(){
        delivered = true;
    }
}
