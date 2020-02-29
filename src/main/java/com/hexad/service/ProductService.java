package com.hexad.service;

import com.hexad.entity.Product;
import com.hexad.service.general.GenericService;

import java.util.List;

/**
 * @author R.fatthi
 */
public interface ProductService extends GenericService<Product, Long> {

    List<Product> orderCustomerProcess(List<Product> products);

}
