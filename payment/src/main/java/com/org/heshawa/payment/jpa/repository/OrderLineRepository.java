package com.org.heshawa.payment.jpa.repository;

import com.org.heshawa.payment.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine,String> {
}
