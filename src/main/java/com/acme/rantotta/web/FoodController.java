package com.acme.rantotta.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.rantotta.domain.Food;
import com.acme.rantotta.service.FoodService;

@Controller
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodService foodService;
    private static int counter = 100;
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    
    @RequestMapping("/food")
    public String list(Model model) {
        logger.warn("LIST");

        model.addAttribute("foodList", foodService.getAll());
        model.addAttribute("food", new Food());
        model.addAttribute("action","add");
        model.addAttribute("method","post");

        return "food/list";
    }
    
    @RequestMapping(value="/food" ,method=RequestMethod.POST)
    public String add(@ModelAttribute Food food, BindingResult result, RedirectAttributes flash ) {
        logger.warn("ADD");

        food.setId("k" + counter++);
        
        if (result.hasErrors()) {
            return "food/list";
        } else {
            foodService.add(food);
            flash.addFlashAttribute("flashMsg", "You have successfullky created: " + food.getName());
            flash.addFlashAttribute("flashType", "success");
            return "redirect:/food";
        }
    }

    @RequestMapping(value="/food-edit-form")
    public String edit(HttpServletRequest request, Model model) {
        logger.warn("EDIT");

        String foodId = request.getParameter("foodId");
        Food foodToEdit = foodService.find(foodId);
        
        model.addAttribute("foodList", foodService.getAll());
        model.addAttribute("food", foodToEdit);
        model.addAttribute("action","update");
        model.addAttribute("method","put");
        
        return "/food/list";
    }


    @RequestMapping(value="/food", method=RequestMethod.PUT)
    public String update(@ModelAttribute Food food, BindingResult result, RedirectAttributes flash ) {
        logger.warn("UPDATE");
        
        if (result.hasErrors()) {
            return "food/list";
        } else {
            foodService.add(food);
            flash.addFlashAttribute("flashMsg", "You have successfullky updated: " + food.getName());
            flash.addFlashAttribute("flashType", "info");

            return "redirect:/food";
        }
    }

    @RequestMapping(value="/food", method=RequestMethod.DELETE)
    public String delete(HttpServletRequest request, RedirectAttributes flash ) {
        String foodId = request.getParameter("foodId");
        logger.warn("DELETE: {}", foodId);
        Food food = foodService.find(foodId);
        logger.warn("deleting food: {}", food);
        foodService.deleteById(foodId);
        
        flash.addFlashAttribute("flashMsg", "You have successfully deleted: " + food.getName());
        flash.addFlashAttribute("flashType", "warning");

        return "redirect:/food";
    }
    

}
