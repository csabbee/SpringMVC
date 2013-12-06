package com.acme.rantotta.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.acme.rantotta.domain.Food;

public class OrderItem {

    @NotEmpty
    private String id;
    @NotNull
    private int quantity;
    private Food food;
    
    public OrderItem(Food food){
        this.id = food.getId();
        this.food = food;
        quantity = 1;
    }

    public String getId() {
        return id;
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

    public Food getFood() {
        return food;
    }
}
