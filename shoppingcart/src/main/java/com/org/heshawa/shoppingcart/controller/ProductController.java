package com.org.heshawa.shoppingcart.controller;

import com.org.heshawa.shoppingcart.service.ProductService;
import com.org.heshawa.shoppingcart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Rest controller to retrieve products and update products
 *
 * NOTE: This class should be a separate micro service in actual system
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    /**
     * Get all products selling at the shop
     * @return All products listed in products table
     */
    @RequestMapping("/all")
    public List<Product> getAllProductItems(){
        return productService.getAllProductItems();
    }

    /**
     * Get all available products at the shop
     * @return Available products at the shop
     */
    @RequestMapping("/available")
    public List<Product> getAvailableProductItems(){
        return productService.getAvailableProductItems();
    }

    /**
     * Get product information
     * @param productId ID of the product
     * @return Product information from the database
     */
    @RequestMapping("/{productId}/info")
    public Product getProductById(@PathVariable String productId){
        return productService.getProductById(productId);
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
        return productService.getDiscountsFromPromotions(productId);
    }
}
