package com.hexad.controller;

import com.hexad.aspect.CheckBindingResult;
import com.hexad.dto.ProductDTO;
import com.hexad.dto.ProductOrderDTO;
import com.hexad.entity.Product;
import com.hexad.mapper.Mapper;
import com.hexad.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author R.fatthi
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
@ApiOperation("for calculate and breakdown a order with products and packs")
public class ProductController {

    private final ProductService productService;

    private final Mapper mapper;

    @GetMapping
    @ApiOperation("get All Products and packs")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    @CheckBindingResult
    @ApiOperation("calculate cost and break down with sending a list of order that must be contain product code  and order number for each product")
    public List<ProductDTO> breakDownProcess(@RequestBody @Valid List<ProductOrderDTO> orderDTOs, BindingResult bindingResult) {
        List<Product> result = productService.orderCustomerProcess(mapper.toProducts(orderDTOs));
        return mapper.toProductDTOs(result);
    }

}
