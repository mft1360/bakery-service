package com.hexad.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * @author R.fatthi
 */
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(name = "uc_productCode", columnNames = {"productCode"}))
@Data
@SuperBuilder
public class Product extends BaseEntity<Long> {

    public Product() {
    }

    private String productName;

    private String productCode;

    @OneToMany(targetEntity = Pack.class, mappedBy = "product")
    private List<Pack> packs;

    @Transient
    private int customerOrder;

    @Transient
    private double totalPrice;

}
