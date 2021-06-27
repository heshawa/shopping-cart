package com.org.heshawa.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "productId", nullable = false, length = 20)
    private String productId;

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "unitPrice", nullable = false, precision = 2)
    private BigDecimal unitPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(unitPrice, product.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, unitPrice);
    }
}
