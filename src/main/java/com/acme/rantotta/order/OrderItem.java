package com.acme.rantotta.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderItem {


    @NotEmpty
    private String id;
    @NotNull
    private int quantity;
    
    public OrderItem(){}

    public OrderItem(String id){
        this.id = id;
        quantity = 1;
    }
    public String getId() {
        return id;
    }
    
    public void setFoodId(String id){
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity > 0){
            this.quantity = quantity;
        } else {
            throw new IllegalStateException("OrderItem's quantity must be not negative");
        }
    }
    
    public void incrementQuantity(){
        quantity++;
    }
    
    public void decrementQuantity(){
        quantity--;
    }
}
