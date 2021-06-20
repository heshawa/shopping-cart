package com.org.heshawa.shoppingcart.controller;

import com.org.heshawa.shoppingcart.dto.request.body.OrderLineRequest;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartUpdateBody;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.jpa.repository.OrderLineRepository;
import com.org.heshawa.shoppingcart.jpa.repository.ProductRepository;
import com.org.heshawa.shoppingcart.jpa.repository.ShoppingCartRepository;
import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.Product;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError.*;

/**
 * Rest controller to retrieve order lines in shopping carts and
 * perform operations on shopping cart
 */
@RestController
@RequestMapping("/cart/orderline/")
public class UpdateShoppingCart {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Add delete or update order line in a given shopping art
     * @param cartId ID of the cart to update
     * @param orderLineRequestBody Order line values to update
     */
    @PostMapping("/update/{cartId}")
    public ShoppingCartUpdateBody addItemsToCart(@PathVariable String cartId, @RequestBody OrderLineRequest orderLineRequestBody){
        ShoppingCart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productRepository.findById(orderLineRequestBody.getProductId()).orElse(null);

        if (cart == null){
            throw new ShoppingCartException(CART_NOT_FOUND);
        }else if (product == null){
            throw new ShoppingCartException(PRODUCT_NOT_FOUND);
        }
        OrderLine orderLineInCart = orderLineRepository.findByShoppingCartIdAndProductId(cart,product);

        //Insert order line
        if(orderLineInCart == null){
            orderLineInCart = new OrderLine();
            orderLineInCart.setShoppingCartId(cart);
            orderLineInCart.setProductId(product);
            orderLineInCart.setQuantity(orderLineRequestBody.getQuantity());
            orderLineInCart.calculatePrice();
            orderLineInCart.setOrderLineId(cartId.concat("-").concat(String.valueOf(Math.round(Math.random()*100000))));
            orderLineRepository.save(orderLineInCart);
            cart.increaseCartItemCount();
            cart.increaseCartValue(orderLineInCart.getPrice());
        }else if(orderLineRequestBody.getQuantity().compareTo(BigDecimal.ZERO) == 0){ //Delete order line
            orderLineRepository.delete(orderLineInCart);
            cart.decreaseCartItemCount();
            cart.increaseCartValue(orderLineInCart.getPrice());
        } else if(!orderLineRequestBody.getQuantity().equals(orderLineInCart.getQuantity())){ //Update order line
            cart.decreaseCartValue(orderLineInCart.getPrice());
            orderLineInCart.setQuantity(orderLineRequestBody.getQuantity());
            orderLineInCart.calculatePrice();
            orderLineRepository.save(orderLineInCart);
            cart.increaseCartValue(orderLineInCart.getPrice());
        }
        cartRepository.save(cart);
        ShoppingCartUpdateBody response = new ShoppingCartUpdateBody();
        response.setStatus("SUCCESS");

        return response;
    }

    /**
     * Add order lines from saved cart to primary cart
     * @param savedCartId ID of the cart to retrieve order lines
     * @param primaryCart ID of the primary cart to add order lines
     */
    @RequestMapping("/from/{savedCartId}/to/{primaryCart}")
    public void addOrderLinesFromSavedCart(@PathVariable String savedCartId, @PathVariable String primaryCart){
        //TODO Validate user and cart IDs
        //TODO Retrieve order lines from saved cart
        //TODO Add them into primary cart
    }

}
