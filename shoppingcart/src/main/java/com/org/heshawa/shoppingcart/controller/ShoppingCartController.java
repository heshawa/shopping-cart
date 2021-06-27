package com.org.heshawa.shoppingcart.controller;

import com.org.heshawa.shoppingcart.service.ShoppingCartService;
import com.org.heshawa.shoppingcart.dto.request.body.OrderLineRequest;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartResponse;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartUpdateBody;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to retrieve shopping carts created by customers
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Retrieve shopping carts belongs to specific customer
     * @param customerId Customer Id to find shopping carts
     * @return List of shopping carts created by the customer
     */
    @RequestMapping("/{customerId}/carts")
    public List<ShoppingCart> getShoppingCarts(@PathVariable String customerId) throws ShoppingCartException {
        return shoppingCartService.getShoppingCarts(customerId);
    }

    /**
     * Retrieve shopping cart by the shopping cart ID
     * @param cartId Cart ID of the shopping cart
     * @return Single shopping cart with provided cart ID
     */
    @RequestMapping("/{cartId}")
    public ShoppingCartResponse getSingleShoppingCart(@PathVariable String cartId){
        return shoppingCartService.getSingleShoppingCart(cartId);
    }

    /**
     * Retrieve primary cart of the customer. Create new cart if customer doesn't have a cart
     * @param customerId ID of the customer
     * @return primary cart of the customer
     */
    @RequestMapping("/primary/{customerId}")
    public ShoppingCart createShoppingCart(@PathVariable String customerId){
        return shoppingCartService.createShoppingCart(customerId);
    }

    /**
     * Retrieve order lines in cart with all the data
     * @param cartId ID of the cart to retrieve order lines
     * @return Order lines in the cart
     */
    @RequestMapping("/{cartId}/order/lines")
    public List<OrderLine> getOrderLinesInCart(@PathVariable String cartId){
        return shoppingCartService.getOrderLinesInCart(cartId);
    }

    /**
     * Save primary shopping cart
     * @param customerId ID of the shopping cart owner
     * @param cartName Cart name to save the cart
     */
    @RequestMapping("/{customerId}/save/{cartName}")
    public void saveCurrentShoppingCart(@PathVariable String customerId, @PathVariable String cartName){
        //TODO Add optional cart name column to shoppingcart table
        //TODO Validate primary cart has order lines
        //TODO Set isPrimary 0, Set cart name and save cart with new cart ID
        //TODO Update cart ID of the order lines to new cart ID
        //NOTE: This will reset current cart. Implement UpdateShoppingCart.addOrderLinesFromSavedCart to use saved shopping cart
    }

    /**
     * Add delete or update order line in a given shopping art
     * @param cartId ID of the cart to update
     * @param orderLineRequestBody Order line values to update
     */
    @PostMapping("/orderline/update/{cartId}")
    public ShoppingCartUpdateBody addItemsToCart(@PathVariable String cartId, @RequestBody OrderLineRequest orderLineRequestBody){
        return shoppingCartService.addItemsToCart(cartId, orderLineRequestBody);
    }

    /**
     * Add order lines from saved cart to primary cart
     * @param savedCartId ID of the cart to retrieve order lines
     * @param primaryCart ID of the primary cart to add order lines
     */
    @RequestMapping("/orderline/from/{savedCartId}/to/{primaryCart}")
    public void addOrderLinesFromSavedCart(@PathVariable String savedCartId, @PathVariable String primaryCart){
        //TODO Validate user and cart IDs
        //TODO Retrieve order lines from saved cart
        //TODO Add them into primary cart
    }

}
