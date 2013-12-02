package com.acme.rantotta.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.rantotta.domain.Address;

@Controller
public class AddressController {

    @RequestMapping("/address")
    public String address() {
        return "address/show";
    }
    
    @RequestMapping("/address/set")
    public String set(HttpServletRequest request, HttpSession session) {
        Address address = new Address();
        
        address.setCity(request.getParameter("city"));
        address.setStreet(request.getParameter("street"));
        address.setZip(request.getParameter("zip"));
        
        session.setAttribute("address", address);
        return "redirect:/address";
    }
    
}
