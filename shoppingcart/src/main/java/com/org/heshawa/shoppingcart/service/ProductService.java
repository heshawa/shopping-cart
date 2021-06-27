package com.org.heshawa.shoppingcart.service;

import com.org.heshawa.shoppingcart.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<Product> getAllProductItems();
    List<Product> getAvailableProductItems();
    Product getProductById(String productId);
    BigDecimal getDiscountsFromPromotions(String productId);
}
