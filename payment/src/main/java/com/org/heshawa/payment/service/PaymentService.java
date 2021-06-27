package com.org.heshawa.payment.service;

import com.org.heshawa.payment.model.Order;

public interface PaymentService {
    void createOrderForCustomer(String customerId);
    Order getOrder(String orderId);
}
