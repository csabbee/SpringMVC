package com.acme.rantotta.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acme.rantotta.exception.OrderServiceException;
import com.acme.rantotta.order.Order;
import com.acme.rantotta.order.OrderItem;
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
        
        return "{\"id\":" +service.getCartIdBySessionId(cookieMap.get("JSESSIONID"))+ "}";
    }
    
    @RequestMapping(value="/cart/{cartId}", method=RequestMethod.PUT)
    @ResponseStatus(value=HttpStatus.ACCEPTED)
    public String putOrderItemInOrder(@PathVariable Integer cartId, @ModelAttribute String foodId, 
            @ModelAttribute int quantity){
        Order order = service.getOrderById(cartId);
        OrderItem orderItem = new OrderItem(service.getFoodById(foodId).getId());
        orderItem.setQuantity(quantity);
        order.addOrderItemToOrder(orderItem);
        return "/food";
    }
    
    @RequestMapping(value="/cart/{cartId}", method=RequestMethod.PUT, produces=PRODUCES_JSON)
    @ResponseStatus(value=HttpStatus.ACCEPTED)
    @ResponseBody
    public String putOrderItemInOrderFromJson(@PathVariable Integer cartId, 
            @RequestBody String json) throws JsonParseException, JsonMappingException, IOException{
        logger.warn("cartId: {}", cartId);
        String decodedJson = URLDecoder.decode(json);
        logger.warn("decodedJson: {}", decodedJson);
        ObjectMapper mapper = new ObjectMapper();
        OrderItem orderItem = mapper.readValue(decodedJson, OrderItem.class);
        logger.warn("orderitem: {}", orderItem);
        Order order = service.getOrderById(cartId);
        order.addOrderItemToOrder(orderItem);
        return "{\"status\":\"ok\"}";
    }
    
    @ExceptionHandler(OrderServiceException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleOrderException(Exception e){
        return e.getMessage();
    }
    
    @RequestMapping(value="/cart/{cartId}", method=RequestMethod.POST, produces=PRODUCES_JSON,
            headers="content-length=0")
    @ResponseStatus(value=HttpStatus.OK)
    @ResponseBody
    public String checkOutCarFromJson(@PathVariable Integer cartId){
        service.checkOutCart(cartId);
        return "todo...";
    }
}
