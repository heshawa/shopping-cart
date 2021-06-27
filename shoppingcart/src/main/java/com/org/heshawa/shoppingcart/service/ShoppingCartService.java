package com.org.heshawa.shoppingcart.service;

import com.org.heshawa.shoppingcart.dto.request.body.OrderLineRequest;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartResponse;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartUpdateBody;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getShoppingCarts(String customerId) throws ShoppingCartException;
    ShoppingCartResponse getSingleShoppingCart(String cartId);
    ShoppingCart createShoppingCart(String customerId);
    List<OrderLine> getOrderLinesInCart(String cartId);
    ShoppingCartUpdateBody addItemsToCart(String cartId, OrderLineRequest orderLineRequestBody);
}
