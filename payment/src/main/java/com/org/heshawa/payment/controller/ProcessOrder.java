package com.org.heshawa.payment.controller;

import com.org.heshawa.payment.enums.OrderStatus;
import com.org.heshawa.payment.enums.PaymentError;
import com.org.heshawa.payment.exception.PaymentException;
import com.org.heshawa.payment.jpa.repository.OrderLineRepository;
import com.org.heshawa.payment.jpa.repository.OrderRepository;
import com.org.heshawa.payment.model.Order;
import com.org.heshawa.payment.model.OrderLine;
import com.org.heshawa.payment.model.shopping.cart.service.ShoppingCartOrderLine;
import com.org.heshawa.payment.model.shopping.cart.service.ShoppingCart;
import com.org.heshawa.payment.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/order")
public class ProcessOrder {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServiceProperties properties;

    /**
     * Get primary shopping cart and create Order
     * @param customerId ID of the customer to create order
     */
    @RequestMapping("/create/{customerId}")
    public void createOrderForCustomer(@PathVariable String customerId){
        ShoppingCart primaryCart = getShoppingCartFromService(customerId);
        Iterator<ShoppingCartOrderLine> orderLines = getOrderLinesFromShoppingCart(primaryCart.getShoppingCartId());
        List<OrderLine> orderLinesToSave = new ArrayList();

        Order order = orderRepository.findByCustomerIdAndOrderStatusLessThan(customerId
                ,OrderStatus.ORDER_PAYMENT_RECEIVED.getOrderStatus()
        ).orElse(null);

        if(order == null){
            order = Order.builder()
                    .orderId(customerId.concat("-").concat(getRandomID(5)))
                    .customerId(customerId).build();
        }else {
            orderLineRepository.deleteAllByOrderId(order);
        }

        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderValue(BigDecimal.ZERO);
        order.setOrderStatus(OrderStatus.NEW.getOrderStatus());
        order.setDiscountTotal(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ZERO);
        orderRepository.save(order);

        if (orderLines == null){
            throw new PaymentException(PaymentError.SHOPPING_CART_IS_EMPTY);
        }

        Order finalOrder = order;
        orderLines.forEachRemaining(shoppingCartOrderLine -> {
            OrderLine line = OrderLine.builder()
                    .orderLineId(finalOrder.getOrderId().concat("-").concat(getRandomID(5)))
                    .discount(shoppingCartOrderLine.getDiscount())
                    .price(shoppingCartOrderLine.getPrice())
                    .orderId(finalOrder)
                    .productDescription(shoppingCartOrderLine.getProductId().getName())
                    .quantity(shoppingCartOrderLine.getQuantity()).build();
            orderLinesToSave.add(line);
            finalOrder.updateOrder(shoppingCartOrderLine);
        });

        //TODO Calculate and add costs
        orderLineRepository.saveAll(orderLinesToSave);
        order.setOrderStatus(OrderStatus.ITEMS_UPDATED_FROM_CART.getOrderStatus());
        orderRepository.save(finalOrder);
    }

    /**
     * Get order details
     * @param orderId Order ID to retrieve order details
     * @return
     */
    @RequestMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    /**
     * Generate and return numeric string
     * @param numberOfCharacters Number of characters requires
     * @return String with required length
     */
    private String getRandomID(int numberOfCharacters){
        return String.valueOf(Math.round(Math.random()*100000));
    }

    /**
     * Get primary shopping cart by calling external service
     * @param customerId ID of the customer to get shopping cart
     * @return Primary shopping cart of the customer
     */
    private ShoppingCart getShoppingCartFromService(String customerId){
        ShoppingCart primaryCart = null;
        try {
            primaryCart = restTemplate.getForObject(properties.shoppingCartServiceUrl
                    .concat("/cart/primary/")
                    .concat(customerId), ShoppingCart.class);
        }catch (Exception ex){
            throw new PaymentException(PaymentError.SHOPPING_CART_RETRIEVAL_FAILED);
        }
        return primaryCart;
    }

    /**
     * Get order lines from a shopping cart by calling external service
     * @param cartId ID of the cart to retrieve order lines
     * @return Order lines in the cart
     */
    private Iterator<ShoppingCartOrderLine> getOrderLinesFromShoppingCart(String cartId){
        ShoppingCartOrderLine[] shoppingCartOrderLinesInCart = null;
        try {
            shoppingCartOrderLinesInCart = restTemplate.getForObject(properties.shoppingCartServiceUrl
                    .concat("/cart/")
                    .concat(cartId)
                    .concat("/order/lines"), ShoppingCartOrderLine[].class);
        }catch (Exception ex){
            throw new PaymentException(PaymentError.ORDER_LINES_RETRIEVAL_FAILED);
        }
        if (shoppingCartOrderLinesInCart.length == 0){
            return null;
        }
        return Arrays.stream(shoppingCartOrderLinesInCart).iterator();
    }
}
