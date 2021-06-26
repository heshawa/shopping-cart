package com.org.heshawa.payment.model;

import com.org.heshawa.payment.model.shopping.cart.service.ShoppingCartOrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private Date orderDate;
    private Date deliverDate;
    private BigDecimal orderValue;
    private BigDecimal discountTotal;
    private Integer orderStatus;
    private String customerId;
    private BigDecimal totalTax;

    @Id
    @Column(name = "orderId", nullable = false, unique = true)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "orderDate", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "deliverDate", nullable = true)
    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    @Basic
    @Column(name = "orderValue", nullable = false, precision = 2)
    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    @Basic
    @Column(name = "discountTotal", nullable = true, precision = 2)
    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    @Basic
    @Column(name = "orderStatus", nullable = false)
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "customerId", nullable = false, length = 20)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "totalTax", nullable = false, precision = 2)
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

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
