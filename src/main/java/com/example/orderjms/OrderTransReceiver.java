package com.example.orderjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderTransReceiver {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerOrderRepository customerRepo;

    @JmsListener(destination = "orderQ", containerFactory = "myFactory")
    public void receiveMessage(Order transaction){
        System.out.println("Received<" + transaction + ">");
        orderRepo.save(transaction);
    }

    @JmsListener(destination = "updateQ", containerFactory = "myFactory")
    public void recvUpdateMessage(Order transaction){
        System.out.println("Received update trans <" + transaction + ">");
        customerRepo.updateOrderAmount(transaction.getFrom(), transaction.getProduct(), transaction.getAmount());
    }
}