package com.org.heshawa.shoppingcart.controller;

import com.org.heshawa.shoppingcart.jpa.repository.ProductRepository;
import com.org.heshawa.shoppingcart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller to retrieve products and update products
 *
 * NOTE: This class should be a separate micro service in actual system
 */
@RestController
@RequestMapping("/products")
public class GetProducts {

    @Autowired
    private ProductRepository productRepository;
    /**
     * Get all products selling at the shop
     * @return All products listed in products table
     */
    @RequestMapping("/all")
    public List<Product> getAllProductItems(){
        List<Product> allProducts = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(product -> {
            allProducts.add(product);
        });
        return allProducts;
    }

    /**
     * Get all available products at the shop
     * @return Available products at the shop
     */
    @RequestMapping("/available")
    public List<Product> getAvailableProductItems(){
        List<Product> availableProducts = new ArrayList<>();
        productRepository.findByAvailableGreaterThan(new BigDecimal(10)).iterator().forEachRemaining(product -> {
            availableProducts.add(product);
        });
        return availableProducts;
    }

    /**
     * Get product information
     * @param productId ID of the product
     * @return Product information from the database
     */
    @RequestMapping("/{productId}/info")
    public Product getProductById(@PathVariable String productId){
        return productRepository.findById(productId).orElse(null);
    }

    /**
     * Get discount vales for products
     *
     * @param productId ID of the product to find discount values
     * @return discount amount for product
     */
    @RequestMapping("/{productId}/promotion")
    public BigDecimal getDiscountsFromPromotions(@PathVariable String productId){
        //TODO Create promotions table
        //TODO calculate and return discount value for product
        return null;
    }
}
