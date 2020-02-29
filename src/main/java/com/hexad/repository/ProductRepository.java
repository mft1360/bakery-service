package com.hexad.repository;

import com.hexad.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author R.fatthi
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductCodeIn(List<String> productCodes);

}
