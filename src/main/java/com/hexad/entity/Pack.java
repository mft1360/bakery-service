package com.hexad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * @author R.fatthi
 */
@Entity
@Table(name = "packs")
@Data
@SuperBuilder
public class Pack extends BaseEntity<Long> {

    public Pack() {
    }

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product")
    @JsonIgnore
    private Product product;

    private int countPack;

    private double pricePack;

    @Transient
    private int countProcess;

}
