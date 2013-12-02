package com.acme.rantotta.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BreakController {

    @RequestMapping("/list")
    public String list() {
        return "list";
    }
}
