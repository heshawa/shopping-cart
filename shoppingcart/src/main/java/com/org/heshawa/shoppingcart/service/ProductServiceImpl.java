package com.org.heshawa.shoppingcart.service;

import com.org.heshawa.shoppingcart.jpa.repository.ProductRepository;
import com.org.heshawa.shoppingcart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProductItems() {
        List<Product> allProducts = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(allProducts::add);
        return allProducts;
    }

    @Override
    public List<Product> getAvailableProductItems() {
        List<Product> availableProducts = new ArrayList<>();
        productRepository.findByAvailableGreaterThan(new BigDecimal(10)).iterator().forEachRemaining(
                availableProducts::add
        );
        return availableProducts;
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public BigDecimal getDiscountsFromPromotions(String productId) {
        return null;
    }
}
