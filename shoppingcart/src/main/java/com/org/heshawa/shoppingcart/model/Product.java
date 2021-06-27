package com.org.heshawa.shoppingcart.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "productId")
    private String productId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;

    @Basic
    @Column(name = "available")
    private BigDecimal available;

    @Basic
    @Column(name = "sold")
    private BigDecimal sold;

    @Basic
    @Column(name = "reserved")
    private BigDecimal reserved;

    @Basic
    @Column(name = "isUnit")
    private Boolean isUnit;

    @Basic
    @Column(name = "tax", nullable = false)
    private BigDecimal tax;
}
