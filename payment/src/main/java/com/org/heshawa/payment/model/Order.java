package com.org.heshawa.payment.model;

import com.org.heshawa.payment.dto.response.body.cart.ShoppingCartOrderLine;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "orderId", nullable = false, unique = true)
    private String orderId;

    @Basic
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Basic
    @Column(name = "deliverDate")
    private Date deliverDate;

    @Basic
    @Column(name = "orderValue", nullable = false, precision = 2)
    private BigDecimal orderValue;

    @Basic
    @Column(name = "discountTotal", precision = 2)
    private BigDecimal discountTotal;

    @Basic
    @Column(name = "orderStatus", nullable = false)
    private Integer orderStatus;

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    private String customerId;

    @Basic
    @Column(name = "totalTax", nullable = false, precision = 2)
    private BigDecimal totalTax;

    public void updateOrder(ShoppingCartOrderLine orderLine){
        this.orderValue = this.orderValue.add(orderLine.getPrice());
        this.discountTotal = this.discountTotal.add(orderLine.getDiscount());
        this.totalTax = this.totalTax.add(
                orderLine.getPrice()
                        .multiply(orderLine.getProductId().getTax())
                        .divide(BigDecimal.valueOf(100))
        );
    }

}
