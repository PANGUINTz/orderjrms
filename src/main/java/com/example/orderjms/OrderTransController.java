package com.example.orderjms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderTransController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private OrderRepository orderRepo;

    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @PostMapping("/send")
    public void send(@RequestBody Order transaction) {
        System.out.println("Sending a transaction!");
        jmsTemplate.convertAndSend("orderQ", transaction);
    }

    @PutMapping("/modify")
    public void modify(@RequestBody Order transaction) {
        System.out.println("Sending an updated transaction!");
        jmsTemplate.convertAndSend("updateQ", transaction);
    }
}
