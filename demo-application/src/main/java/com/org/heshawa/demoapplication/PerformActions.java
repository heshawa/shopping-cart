package com.org.heshawa.demoapplication;

import com.org.heshawa.demoapplication.dto.request.body.AddItemRequest;
import com.org.heshawa.demoapplication.dto.request.body.CustomerRequest;
import com.org.heshawa.demoapplication.dto.response.body.CustomerResponse;
import com.org.heshawa.demoapplication.dto.response.body.OrderLineResponse;
import com.org.heshawa.demoapplication.dto.response.body.ShoppingCartResponse;
import com.org.heshawa.demoapplication.properties.ServiceProperties;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/executeUserCartCreation")
public class PerformActions {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServiceProperties properties;

    @RequestMapping
    public void createUserAndAdditemsToCart(){
        CustomerRequest userDetails1 = CustomerRequest.builder()
                .name("Paul Powel")
                .email("paul@gmail.com")
                .username("ppowel")
                .password("Abc123")
                .houseNumber("15/1")
                .addressLine1("1st Lane")
                .addressLine2("")
                .city("Moratuwa")
                .contactCountryCode("+94")
                .contactNumber("0777478789").build();

        CustomerRequest userDetails2 = CustomerRequest.builder()
                .name("Dean Winchester")
                .email("dean@gmail.com")
                .username("deanw")
                .password("Abc1223")
                .houseNumber("150")
                .addressLine1("2nd Lane")
                .addressLine2("Rocker road")
                .city("Moratuwa")
                .contactCountryCode("+94")
                .contactNumber("0707478789").build();

        //Create customer
        CustomerResponse registeredUser1 = restTemplate.postForObject(
                properties.customerServiceUrl.concat("/customer/register"),userDetails1,CustomerResponse.class
        );

        CustomerResponse registeredUser2 = restTemplate.postForObject(
                properties.customerServiceUrl.concat("/customer/register"),userDetails2,CustomerResponse.class
        );

        //Get customer shopping cart
        ShoppingCartResponse shoppingCart1 = restTemplate.getForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/primary/")
                        .concat(registeredUser1.getCustomerID())
                ,ShoppingCartResponse.class
        );
        ShoppingCartResponse shoppingCart2 = restTemplate.getForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/primary/")
                        .concat(registeredUser2.getCustomerID())
                ,ShoppingCartResponse.class
        );

        //Add items to shopping cart
        //Item1
        AddItemRequest addItem1 = AddItemRequest.builder()
                .productId("FISH11134")
                .quantity(1.5).build();

        //User1
        restTemplate.postForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/orderline/update/")
                        .concat(shoppingCart1.getShoppingCartId())
                ,addItem1
                ,JSONObject.class
        );

        //NOTE Items can retrieve and display in front end. Following data requires to send back end to insert DB
        //Add items to shopping cart
        //Item1
        AddItemRequest addItem2 = AddItemRequest.builder()
                .productId("VEG12344")
                .quantity(0.785).build();


        //Item3
        AddItemRequest addItem3 = AddItemRequest.builder()
                .productId("152632")
                .quantity(4.0).build();

        //User 2
        //Add 2 items
        restTemplate.postForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/orderline/update/")
                        .concat(shoppingCart2.getShoppingCartId())
                ,addItem2
                ,JSONObject.class
        );
        restTemplate.postForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/orderline/update/")
                        .concat(shoppingCart2.getShoppingCartId())
                ,addItem3
                ,JSONObject.class
        );

        //Get updated cart
        shoppingCart1 = restTemplate.getForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/")
                        .concat(shoppingCart1.getShoppingCartId())
                ,ShoppingCartResponse.class
        );

        shoppingCart2 = restTemplate.getForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/")
                        .concat(shoppingCart2.getShoppingCartId())
                ,ShoppingCartResponse.class
        );

        System.out.println("User1: Shopping cart total: "+shoppingCart1.getCartValue());
        System.out.println("User2: Shopping cart total: "+shoppingCart2.getCartValue());
        System.out.println("***********************************************************************");
        System.out.println("User1: Shopping cart json");
        System.out.println(shoppingCart1.toString());
        System.out.println("User2: Shopping cart json");
        System.out.println(shoppingCart2.toString());

    }
}
