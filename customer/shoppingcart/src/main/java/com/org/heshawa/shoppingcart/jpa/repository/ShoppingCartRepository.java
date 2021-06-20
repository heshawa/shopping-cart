package com.org.heshawa.shoppingcart.jpa.repository;

import com.org.heshawa.shoppingcart.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
    List<ShoppingCart> findByCustomerIdAndIsPrimary(String customerId, Boolean isPrimary);
    List<ShoppingCart> findByCustomerId(String customerId);

}
