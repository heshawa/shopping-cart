package com.org.heshawa.shoppingcart.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "product")
public class Product {
    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal available;
    private BigDecimal sold;
    private BigDecimal reserved;
    private Boolean isUnit;
    private BigDecimal tax;

    @Id
    @Column(name = "productId")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unitPrice")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "available")
    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    @Basic
    @Column(name = "sold")
    public BigDecimal getSold() {
        return sold;
    }

    public void setSold(BigDecimal sold) {
        this.sold = sold;
    }

    @Basic
    @Column(name = "reserved")
    public BigDecimal getReserved() {
        return reserved;
    }

    public void setReserved(BigDecimal reserved) {
        this.reserved = reserved;
    }

    @Basic
    @Column(name = "isUnit")
    public Boolean getIsUnit() {
        return isUnit;
    }

    public void setIsUnit(Boolean unit) {
        isUnit = unit;
    }

    @Basic
    @Column(name = "tax", nullable = false)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
