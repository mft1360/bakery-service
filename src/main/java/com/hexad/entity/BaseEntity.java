package com.hexad.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base entity class for providing some fields that are public among all of entities
 *
 * @author R.Fattahi
 */
@MappedSuperclass
@Data
@SuperBuilder
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    public BaseEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;


}
