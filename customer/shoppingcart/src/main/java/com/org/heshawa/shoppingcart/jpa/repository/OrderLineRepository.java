package com.org.heshawa.shoppingcart.jpa.repository;

import com.org.heshawa.shoppingcart.model.OrderLine;
import com.org.heshawa.shoppingcart.model.Product;
import com.org.heshawa.shoppingcart.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderLineRepository extends CrudRepository<OrderLine,String> {
    OrderLine findByShoppingCartIdAndProductId(ShoppingCart shoppingCartId, Product productId);
    List<OrderLine> findByShoppingCartId(ShoppingCart shoppingCartId);
}
