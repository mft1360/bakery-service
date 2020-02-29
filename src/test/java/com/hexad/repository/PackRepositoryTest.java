package com.hexad.repository;

import com.hexad.entity.Pack;
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
public class PackRepositoryTest {

    @Autowired
    private PackRepository packRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void whenFindAll_thenReturnPacksList() {
        // when
        List<Pack> packs = packRepository.findAll();

        // then
        assertThat(packs).hasSize(8);
    }


}
