package com.arkar.springrest.controller;

import com.arkar.springrest.model.Order;
import com.arkar.springrest.model.OrderRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyFirstController{

    @GetMapping("/greet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String hello(){
        return "Hello Mother Father.  ";
    }

    @PostMapping("/post")
    public String post(@RequestBody Order order){
        return "Request Accepted and the Message is :"+order.toString();
    }
    @PostMapping("/post-order-record")
    public String post(@RequestBody OrderRecord order){
        return "Request Accepted and the Message is :"+order.toString();
    }
}
