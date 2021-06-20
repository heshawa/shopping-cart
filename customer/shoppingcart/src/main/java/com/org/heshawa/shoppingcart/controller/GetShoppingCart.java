package com.org.heshawa.shoppingcart.controller;

import com.org.heshawa.shoppingcart.dto.responce.body.OrderLineResponse;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartResponse;
import com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.jpa.repository.OrderLineRepository;
import com.org.heshawa.shoppingcart.jpa.repository.ShoppingCartRepository;
import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller to retrieve shopping carts created by customers
 */
@RestController
@RequestMapping("/cart")
public class GetShoppingCart {

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    /**
     * Retrieve shopping carts belongs to specific customer
     * @param customerId Customer Id to find shopping carts
     * @return List of shopping carts created by the customer
     */
    @RequestMapping("/{customerId}/carts")
    public List<ShoppingCart> getShoppingCarts(@PathVariable String customerId){
        List<ShoppingCart> customerCarts = cartRepository.findByCustomerId(customerId);
        if (customerCarts.size() < 1){
            throw new ShoppingCartException(ShoppingCartError.NO_CARTS_FOR_CUSTOMER);
        }
        return customerCarts;
    }

    /**
     * Retrieve shopping cart by the shopping cart ID
     * @param cartId Cart ID of the shopping cart
     * @return Single shopping cart with provided cart ID
     */
    @RequestMapping("/{cartId}")
    public ShoppingCartResponse getSingleShoppingCart(@PathVariable String cartId){
        ShoppingCart cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new ShoppingCartException(ShoppingCartError.CART_NOT_FOUND);
        }

        ShoppingCartResponse response = new ShoppingCartResponse();
        response.createResponseFromModel(cart);
        orderLineRepository.findByShoppingCartId(cart).iterator().forEachRemaining(orderLine -> {
            OrderLineResponse item = new OrderLineResponse();
            item.createResponseFromModel(orderLine);
            response.getOrderLines().add(item);
        });
        return response;
    }

    /**
     * Retrieve primary cart of the customer. Create new cart if customer doesn't have a cart
     * @param customerId ID of the customer
     * @return primary cart of the customer
     */
    @RequestMapping("/primary/{customerId}")
    public ShoppingCart createShoppingCart(@PathVariable String customerId){
        List<ShoppingCart> customerCarts = cartRepository.findByCustomerIdAndIsPrimary(customerId,true);

        if(customerCarts.size()>0){
            return customerCarts.get(0);
        }

        ShoppingCart cart = new ShoppingCart();
        cart.setCartValue(BigDecimal.valueOf(0));
        cart.setCustomerId(customerId);
        cart.setIsPrimary(true);
        cart.setNumberOfItems(0);
        cart.setShoppingCartId(customerId
                .concat("-")
                .concat(String.valueOf(Math.round(Math.random()*100000))));
        cartRepository.save(cart);
        return cart;
    }

    /**
     * Retrieve order lines in cart with all the data
     * @param cartId ID of the cart to retrieve order lines
     * @return Order lines in the cart
     */
    @RequestMapping("/{cartId}/order/lines")
    public List<OrderLine> getOrderLinesInCart(@PathVariable String cartId){
        List<OrderLine> orderLines = new ArrayList<>();
        ShoppingCart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new ShoppingCartException(ShoppingCartError.CART_NOT_FOUND);
        }
        orderLineRepository.findByShoppingCartId(cart).iterator().forEachRemaining(orderLine -> {
            orderLines.add(orderLine);
        });

        return orderLines;
    }

    /**
     * Save primary shopping cart
     * @param customerId
     */
    @RequestMapping("/{customerId}/save/{cartName}")
    public void saveCurrentShoppingCart(@PathVariable String customerId, @PathVariable String cartName){
        //TODO Add optional cart name column to shoppingcart table
        //TODO Validate primary cart has order lines
        //TODO Set isPrimary 0, Set cart name and save cart with new cart ID
        //TODO Update cart ID of the order lines to new cart ID
        //NOTE: This will reset current cart. Implement UpdateShoppingCart.addOrderLinesFromSavedCart to use saved shopping cart
    }


}
