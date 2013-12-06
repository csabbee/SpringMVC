package com.acme.rantotta.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acme.rantotta.order.Order;
import com.acme.rantotta.service.OrderService;

@Controller
@RequestMapping(value="/order", produces="text/html")
public class OrderController {
    
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    private static final String PRODUCES_JSON = "application/json";
    private OrderService service;
    private static AtomicInteger counter = new AtomicInteger(1000);
    
    
    @Autowired
    public OrderController(OrderService service) {
        super();
        this.service = service;
    }
    
    @RequestMapping
    public String listOrder(Model model){
        model.addAttribute("orderList", service.getAll());
        return "/food";
    }
    
    @RequestMapping(produces=PRODUCES_JSON)
    @ResponseBody 
    public String listOrderJSon() throws Exception{
        List<Order> orderList = service.getAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(orderList);
        return json;
    }
    
    @RequestMapping(value="/cart",produces=PRODUCES_JSON, method=RequestMethod.POST)
    @ResponseBody
    public String getCart(HttpServletRequest request){
        logger.warn("request cookies: {}", request.getCookies());
        List<String> cookies = Arrays.asList(request.getHeader("Cookie"));
        Map<String, String> cookieMap = new HashMap<String, String>();
        for (String cookie : cookies) {
            cookieMap.put(cookie.toString().split("=")[0], cookie.toString().split("=")[1]);
        }
        return "{\"id\":\"" +service.getCartIdBySessionId(cookieMap.get("JSESSIONID"))+ "\"}";
    }
}
