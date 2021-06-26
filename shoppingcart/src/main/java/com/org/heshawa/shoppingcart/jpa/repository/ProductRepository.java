package com.org.heshawa.shoppingcart.jpa.repository;

import com.org.heshawa.shoppingcart.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product,String> {
    List<Product> findByAvailableGreaterThan(BigDecimal quantity);
}
