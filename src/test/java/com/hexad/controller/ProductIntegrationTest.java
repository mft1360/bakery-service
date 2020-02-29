package com.hexad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.Application;
import com.hexad.dto.ProductDTO;
import com.hexad.dto.ProductOrderDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.fatthi
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ProductIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void breakDownProcess_IsOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductOrderDTO productOrderDTO1 = ProductOrderDTO.builder().customerOrder(10).productCode("VS5").build();
        ProductOrderDTO productOrderDTO2 = ProductOrderDTO.builder().customerOrder(14).productCode("MB11").build();
        ProductOrderDTO productOrderDTO3 = ProductOrderDTO.builder().customerOrder(13).productCode("CF").build();
        List<ProductOrderDTO> productOrderDTOS = new ArrayList<>();
        productOrderDTOS.add(productOrderDTO1);
        productOrderDTOS.add(productOrderDTO2);
        productOrderDTOS.add(productOrderDTO3);
        MvcResult mvcResult = mvc
                .perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productOrderDTOS)))
                .andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductDTO[] productDTOs = objectMapper.readValue(responseJson, ProductDTO[].class);
        assertThat(productDTOs.length).isEqualTo(3);

    }
}
