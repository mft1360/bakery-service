package com.hexad.repository;

import com.hexad.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void whenFindByProductCodeIn_thenReturnProducts() {
        // when
        List<Product> driverDO = productRepository.findByProductCodeIn(Arrays.asList("MB11"));

        // then
        assertThat(driverDO.size()).isEqualTo(1);
    }

    @Test
    public void whenFindAll_thenReturnProductsList() {
        // when
        List<Product> driverDOS = productRepository.findAll();

        // then
        assertThat(driverDOS).hasSize(3);
    }


}
