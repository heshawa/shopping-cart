package com.org.heshawa.demoapplication;

import com.org.heshawa.demoapplication.dto.request.body.AddItemRequest;
import com.org.heshawa.demoapplication.dto.request.body.CustomerRequest;
import com.org.heshawa.demoapplication.dto.response.body.CustomerResponse;
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
        CustomerRequest userDetails1 = new CustomerRequest();
        userDetails1.setName("Paul Powel");
        userDetails1.setEmail("paul@gmail.com");
        userDetails1.setUsername("ppowel");
        userDetails1.setPassword("Abc123");
        userDetails1.setHouseNumber("15/1");
        userDetails1.setAddressLine1("1st Lane");
        userDetails1.setAddressLine2("");
        userDetails1.setCity("Moratuwa");
        userDetails1.setContactCountryCode("+94");
        userDetails1.setContactNumber("0777478789");

        CustomerRequest userDetails2 = new CustomerRequest();
        userDetails2.setName("Dean Winchester");
        userDetails2.setEmail("dean@gmail.com");
        userDetails2.setUsername("deanw");
        userDetails2.setPassword("Abc1223");
        userDetails2.setHouseNumber("150");
        userDetails2.setAddressLine1("2nd Lane");
        userDetails2.setAddressLine2("Rocker road");
        userDetails2.setCity("Moratuwa");
        userDetails2.setContactCountryCode("+94");
        userDetails2.setContactNumber("0707478789");

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
        AddItemRequest addItem1 = new AddItemRequest();
        addItem1.setProductId("FISH11134");
        addItem1.setQuantity(1.5);

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
        AddItemRequest addItem2 = new AddItemRequest();
        addItem2.setProductId("VEG12344");
        addItem2.setQuantity(0.785);


        //Item3
        AddItemRequest addItem3 = new AddItemRequest();
        addItem3.setProductId("152632");
        addItem3.setQuantity(4.0);

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
                        .concat("/cart/primary/")
                        .concat(registeredUser1.getCustomerID())
                ,ShoppingCartResponse.class
        );

        shoppingCart2 = restTemplate.getForObject(
                properties.shoppingCartServiceUrl
                        .concat("/cart/primary/")
                        .concat(registeredUser2.getCustomerID())
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
