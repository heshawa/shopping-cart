package com.org.heshawa.payment.controller;

import com.org.heshawa.payment.model.Order;
import com.org.heshawa.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private PaymentService paymentService;

    /**
     * Get primary shopping cart and create Order
     * @param customerId ID of the customer to create order
     */
    @RequestMapping("/create/{customerId}")
    public void createOrderForCustomer(@PathVariable String customerId){
        paymentService.createOrderForCustomer(customerId);
    }

    /**
     * Get order details
     * @param orderId Order ID to retrieve order details
     * @return Order details with order lines for provided order ID
     */
    @RequestMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId){
        return paymentService.getOrder(orderId);
    }

}
