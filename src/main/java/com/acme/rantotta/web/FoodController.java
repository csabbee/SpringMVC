package com.acme.rantotta.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.rantotta.domain.Food;
import com.acme.rantotta.service.FoodService;

@Controller
public class FoodController {

    private final FoodService foodService;
    private static int counter = 100;
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    
    @RequestMapping("/food/list")
    public String list(Model model) {
        model.addAttribute("foodList", foodService.getAll());
        model.addAttribute("food", new Food());
        model.addAttribute("action","add");

        return "food/list";
    }
    
    @RequestMapping("/food/update")
    public String update(@ModelAttribute Food food, BindingResult result) {
        
        if (result.hasErrors()) {
            return "food/list";
        } else {
            foodService.add(food);
            return "redirect:/food/list";
        }
    }

    @RequestMapping("/food/add")
    public String add(@ModelAttribute Food food, BindingResult result) {
        food.setId("k" + counter++);
        
        if (result.hasErrors()) {
            return "food/list";
        } else {
            foodService.add(food);
            return "redirect:/food/list";
        }
    }

    @RequestMapping("/food/delete")
    public String delete(HttpServletRequest request) {
        
        String foodId = request.getParameter("foodId");
        foodService.deleteById(foodId);
        
        return "redirect:/food/list";
    }
    
    @RequestMapping("/food/edit")
    public String edit(HttpServletRequest request, Model model) {
        
        String foodId = request.getParameter("foodId");
        Food foodToEdit = foodService.find(foodId);
        
        model.addAttribute("foodList", foodService.getAll());
        model.addAttribute("food", foodToEdit);
        model.addAttribute("action","update");
        
        return "/food/list";
    }

}
