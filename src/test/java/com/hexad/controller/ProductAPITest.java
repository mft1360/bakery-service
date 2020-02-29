package com.hexad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.dto.ProductDTO;
import com.hexad.dto.ProductOrderDTO;
import com.hexad.entity.Product;
import com.hexad.mapper.Mapper;
import com.hexad.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.fatthi
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private Mapper mapper;

    @Test
    public void when_BreakDownProcess_IsOk() throws Exception {
        Product product = Product.builder().id((long) 1).productName("prd").productCode("123").build();
        List<Product> Products = Arrays.asList(product);
        doReturn(Products).when(productService).orderCustomerProcess(any());
        doReturn(Products).when(mapper).toProducts(any());
        ProductDTO productDTO = ProductDTO.builder().productCode("VS5").build();
        List<ProductDTO> productDTOS = Arrays.asList(productDTO);
        doReturn(productDTOS).when(mapper).toProductDTOs(any());
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(ProductOrderDTO.builder().customerOrder(10).productCode("VS5").build()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productCode", is(productDTO.getProductCode())));
    }
}
