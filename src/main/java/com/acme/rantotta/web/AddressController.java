package com.acme.rantotta.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.rantotta.domain.Address;

@Controller
public class AddressController {

    @RequestMapping("/address")
    public String address(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        return "address/show";
    }
    
    @RequestMapping("/address/set")
    public String set(HttpServletRequest request, HttpSession session, 
            @Validated Address address, BindingResult result) {
        if (result.hasErrors()) {
            return "address/show";
        } else {
            session.setAttribute("address", address);
            return "redirect:/address";
        }
    }
    
}
