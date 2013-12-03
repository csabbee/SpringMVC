package com.acme.rantotta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acme.rantotta.domain.Food;

public class FoodService {
    Map<String, Food> foodMap = new HashMap();
    
    public void add(Food food) {
        foodMap.put(food.getId(), food);
    }
    
    public void deleteById(String foodId) {
        foodMap.remove(foodId);
    }
    
    public Food find(String foodId) {
        return foodMap.get(foodId);
    }
    
    public List<Food> getAll() {
        return new ArrayList(foodMap.values());
    }
}
