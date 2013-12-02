package com.acme.rantotta.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.rantotta.service.BreakTimeService;

@Controller
public class BreakController {

    private final BreakTimeService service;
    
    @Autowired
    public BreakController(BreakTimeService service) {
        this.service = service;
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        
        model.addAttribute("breakList", service.getAll());
        model.addAttribute("minutes", service.getMinutesTillNext());
        return "list";
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request) {
        String breakToDelete = request.getParameter("break");
        service.delete(breakToDelete);
        
        return "redirect:/list";
    }
    
    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        String newBreak = request.getParameter("break");
        service.add(newBreak);
        
        return "redirect:/list";
    }
}
