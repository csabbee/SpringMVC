package com.acme.rantotta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.acme.rantotta.domain.Food;
@Service
public class FoodService {
    Map<String, Food> foodMap = new ConcurrentHashMap<String, Food>();
    
    public FoodService() {
        add(new Food("k1", "rantotta", 390));
        add(new Food("k2", "bableves", 780));
        add(new Food("k3", "pacal", 1200));
    }
    
    public void add(final Food food) {
        foodMap.put(food.getId(), food);
    }
    
    public void deleteById(final String foodId) {
        foodMap.remove(foodId);
    }
    
    public Food find(final String foodId) {
        return foodMap.get(foodId);
    }
    
    public List<Food> getAll() {
        return new ArrayList<Food>(foodMap.values());
    }
}
