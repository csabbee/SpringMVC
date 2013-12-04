package com.acme.rantotta.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.rantotta.domain.Food;
import com.acme.rantotta.service.FoodService;

@Controller
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodService foodService;
    private static AtomicInteger counter = new AtomicInteger(100);
    
    
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

    @RequestMapping(value="/food", produces={"text/json", "application/json"})
    public @ResponseBody String listJson() throws JsonGenerationException, JsonMappingException, IOException {
        logger.warn("LIST-JSON");

        List<Food> foodList = foodService.getAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(foodList);

        return json;
    }

    @RequestMapping(value="/food" ,method=RequestMethod.POST)
    public String add(@ModelAttribute Food food, BindingResult result, RedirectAttributes flash ) {
        logger.warn("ADD");

        food.setId("k" + counter.getAndIncrement());
        
        if (result.hasErrors()) {
            return "food/list";
        } else {
            foodService.add(food);
            flash.addFlashAttribute("flashMsg", "You have successfullky created: " + food.getName());
            flash.addFlashAttribute("flashType", "success");
            return "redirect:/food";
        }
    }

    @RequestMapping(value="/food/{foodId}", params="form")
    public String edit(@PathVariable String foodId, Model model) {
        logger.warn("EDIT: id:{} " , foodId);

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

    @RequestMapping(value="/food/{foodId}", method=RequestMethod.DELETE)
    public String delete(@PathVariable String foodId, RedirectAttributes flash ) {
        logger.warn("DELETE: {}", foodId);
        Food food = foodService.find(foodId);
        logger.warn("deleting food: {}", food);
        foodService.deleteById(foodId);
        
        flash.addFlashAttribute("flashMsg", "You have successfully deleted: " + food.getName());
        flash.addFlashAttribute("flashType", "warning");

        return "redirect:/food";
    }

    @RequestMapping(value="/food/{foodId}", produces="text/html")
    public String show(@PathVariable String foodId, Model model) {
        
        model.addAttribute("food", foodService.find(foodId));
        
        return "food/show";
    }

    @RequestMapping(value="/food/{foodId}", produces={"text/json", "application/json"})
    public ResponseEntity<String> showJson(@PathVariable String foodId, Model model) throws Exception {
        
        HttpHeaders responseHeaders = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        Food food = foodService.find(foodId);
        String json = mapper.writeValueAsString(food);
        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/food", method=RequestMethod.POST, produces="text/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) throws Exception {

        HttpHeaders responseHeaders = new HttpHeaders();

        String decodedJson = URLDecoder.decode(json);
        ObjectMapper mapper = new ObjectMapper();
        Food food = mapper.readValue(decodedJson, Food.class);
        
        food.setId("J"+counter.getAndIncrement());
        foodService.add(food);

        return new ResponseEntity<String>("{ok}", responseHeaders, HttpStatus.CREATED);
    }

 }
