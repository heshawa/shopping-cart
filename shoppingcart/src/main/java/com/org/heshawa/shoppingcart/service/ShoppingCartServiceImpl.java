package com.org.heshawa.shoppingcart.service;

import com.org.heshawa.shoppingcart.dto.request.body.OrderLineRequest;
import com.org.heshawa.shoppingcart.dto.responce.body.CustomerResponse;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartResponse;
import com.org.heshawa.shoppingcart.dto.responce.body.ShoppingCartUpdateBody;
import com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError;
import com.org.heshawa.shoppingcart.exception.ShoppingCartException;
import com.org.heshawa.shoppingcart.jpa.repository.OrderLineRepository;
import com.org.heshawa.shoppingcart.jpa.repository.ProductRepository;
import com.org.heshawa.shoppingcart.jpa.repository.ShoppingCartRepository;
import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.Product;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import com.org.heshawa.shoppingcart.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError.CART_NOT_FOUND;
import static com.org.heshawa.shoppingcart.enums.exception.ShoppingCartError.PRODUCT_NOT_FOUND;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceProperties properties;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ShoppingCart> getShoppingCarts(String customerId) throws ShoppingCartException {
        List<ShoppingCart> customerCarts = cartRepository.findByCustomerId(customerId);
        if (customerCarts.size() < 1){
            throw new ShoppingCartException(ShoppingCartError.NO_CARTS_FOR_CUSTOMER);
        }
        return customerCarts;
    }

    @Override
    public ShoppingCartResponse getSingleShoppingCart(String cartId){
        ShoppingCart cart = cartRepository.findById(cartId).orElse(null);
        if(cart == null){
            throw new ShoppingCartException(ShoppingCartError.CART_NOT_FOUND);
        }

        ShoppingCartResponse response = new ShoppingCartResponse();
        response.createResponseFromModel(cart);
        orderLineRepository.findByShoppingCartId(cart).iterator().forEachRemaining(
                response::addOrderLineResponseItem
        );
        return response;
    }

    @Override
    public ShoppingCart createShoppingCart(String customerId){
        getCustomerFromCustomerServce(customerId);
        List<ShoppingCart> customerCarts = cartRepository.findByCustomerIdAndIsPrimary(customerId,true);

        if(customerCarts.size()>0){
            return customerCarts.get(0);
        }

        ShoppingCart cart = ShoppingCart.builder()
                .cartValue(BigDecimal.valueOf(0))
                .customerId(customerId)
                .isPrimary(true)
                .numberOfItems(0)
                .shoppingCartId(customerId
                        .concat("-")
                        .concat(String.valueOf(Math.round(Math.random()*100000)))).build();
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public List<OrderLine> getOrderLinesInCart(String cartId){
        List<OrderLine> orderLines = new ArrayList<>();
        ShoppingCart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null){
            throw new ShoppingCartException(ShoppingCartError.CART_NOT_FOUND);
        }
        orderLineRepository.findByShoppingCartId(cart).iterator().forEachRemaining(
                orderLines::add
        );

        return orderLines;
    }

    @Override
    public ShoppingCartUpdateBody addItemsToCart(String cartId, OrderLineRequest orderLineRequestBody){
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
            orderLineInCart = OrderLine.builder()
                    .shoppingCartId(cart)
                    .productId(product)
                    .orderLineId(cartId.concat("-").concat(String.valueOf(Math.round(Math.random()*100000)))).build();
            orderLineInCart.setQuantity(orderLineRequestBody.getQuantity());

            orderLineRepository.save(orderLineInCart);
            cart.updateCart(null,orderLineInCart);
        }else if(orderLineRequestBody.getQuantity().compareTo(BigDecimal.ZERO) == 0){ //Delete order line
            orderLineRepository.delete(orderLineInCart);
            cart.updateCart(orderLineInCart,null);
        } else if(!orderLineRequestBody.getQuantity().equals(orderLineInCart.getQuantity())){ //Update order line
            OrderLine oldOrderLine = OrderLine.builder()
                    .price(orderLineInCart.getPrice()).build();
            orderLineInCart.setQuantity(orderLineRequestBody.getQuantity());
            orderLineRepository.save(orderLineInCart);
            cart.updateCart(oldOrderLine,orderLineInCart);
        }
        cartRepository.save(cart);
        ShoppingCartUpdateBody response = new ShoppingCartUpdateBody();
        response.setStatus("SUCCESS");

        return response;
    }

    private CustomerResponse getCustomerFromCustomerServce(String customerId){
        CustomerResponse customer;
        try{
            customer = restTemplate.getForObject(
                    properties.customerServiceUrl.concat("/customer/").concat(customerId),
                    CustomerResponse.class
            );
        }catch (HttpClientErrorException ex){
            throw new ShoppingCartException(ShoppingCartError.CUSTOMER_NOT_FOUND);
        }

        return customer;
    }

}
