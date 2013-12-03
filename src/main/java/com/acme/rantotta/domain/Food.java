package com.acme.rantotta.domain;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;


public class Food {

    @NotEmpty
    private String id;
    
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    @Min(50)
    private float price;
    private int calories;

    public Food(String id, String name, float price, int calories) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Food() {

    }

    public Food(String id, String name, float price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
