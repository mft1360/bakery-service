package com.hexad.service;

import com.hexad.entity.Pack;
import com.hexad.entity.Product;
import com.hexad.exception.BusinessException;
import com.hexad.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * @author m.fatthi
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void orderCustomerProcessTest_IsOk() {
        List<Product> products = Arrays.asList(Product.builder().productCode("VS5")
                .packs(Arrays.asList(Pack.builder().countPack(5).pricePack(9.55).build())).build());
        doReturn(products).when(productRepository).findByProductCodeIn(any());
        List<Product> finalProducts = productService.orderCustomerProcess(Arrays.asList(Product.builder().productCode("VS5").customerOrder(15).build()));
        double totalPrice = finalProducts.get(0).getTotalPrice();
        assertThat(totalPrice).isEqualTo(new BigDecimal(9.55 * 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    @Test
    public void orderCustomerProcessTest_ByAnotherValue_IsOk() {
        int order = 13;
        Pack pack1 = Pack.builder().countPack(3).pricePack(5.95).build();
        Pack pack2 = Pack.builder().countPack(5).pricePack(9.95).build();
        Pack pack3 = Pack.builder().countPack(9).pricePack(16.99).build();
        Product product1 = Product.builder().productCode("CF").packs(new ArrayList<>()).build();
        product1.getPacks().add(pack1);
        product1.getPacks().add(pack2);
        product1.getPacks().add(pack3);
        List<Product> products = Arrays.asList(product1);
        doReturn(products).when(productRepository).findByProductCodeIn(any());
        List<Product> finalProducts = productService.orderCustomerProcess(Arrays.asList(Product.builder().productCode("CF").customerOrder(order).build()));
        double totalPrice = finalProducts.get(0).getTotalPrice();
        assertThat(totalPrice).isEqualTo(25.85);
    }


    @Test
    public void orderCustomerProcessTest_ByMultipleOrder_IsOk() {
        Pack p1Pack1 = Pack.builder().countPack(3).pricePack(6.99).build();
        Pack p1Pack2 = Pack.builder().countPack(5).pricePack(8.99).build();
        Pack p2Pack1 = Pack.builder().countPack(2).pricePack(9.95).build();
        Pack p2Pack2 = Pack.builder().countPack(5).pricePack(16.95).build();
        Pack p2Pack3 = Pack.builder().countPack(8).pricePack(24.95).build();
        Pack p3Pack1 = Pack.builder().countPack(3).pricePack(5.95).build();
        Pack p3Pack2 = Pack.builder().countPack(5).pricePack(9.95).build();
        Pack p3Pack3 = Pack.builder().countPack(9).pricePack(16.99).build();
        Product product1 = Product.builder().productCode("VS5").packs(new ArrayList<>()).build();
        Product product2 = Product.builder().productCode("MB11").packs(new ArrayList<>()).build();
        Product product3 = Product.builder().productCode("CF").packs(new ArrayList<>()).build();
        product1.getPacks().add(p1Pack1);
        product1.getPacks().add(p1Pack2);
        product2.getPacks().add(p2Pack1);
        product2.getPacks().add(p2Pack2);
        product2.getPacks().add(p2Pack3);
        product3.getPacks().add(p3Pack1);
        product3.getPacks().add(p3Pack2);
        product3.getPacks().add(p3Pack3);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        doReturn(products).when(productRepository).findByProductCodeIn(any());
        List<Product> productOrders = new ArrayList<>();
        productOrders.add(Product.builder().productCode("VS5").customerOrder(10).build());
        productOrders.add(Product.builder().productCode("MB11").customerOrder(14).build());
        productOrders.add(Product.builder().productCode("CF").customerOrder(13).build());
        List<Product> finalProducts = productService.orderCustomerProcess(productOrders);
        double sumPrice = finalProducts.stream()
                .map(x -> x.getTotalPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        assertThat(sumPrice).isEqualTo(98.63);
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowException_when_OrderIs7() {
        Pack p1Pack1 = Pack.builder().countPack(3).pricePack(6.99).build();
        Pack p1Pack2 = Pack.builder().countPack(5).pricePack(8.99).build();
        Product product1 = Product.builder().productCode("VS5").packs(new ArrayList<>()).build();
        product1.getPacks().add(p1Pack1);
        product1.getPacks().add(p1Pack2);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        doReturn(products).when(productRepository).findByProductCodeIn(any());
        List<Product> productOrders = new ArrayList<>();
        productOrders.add(Product.builder().productCode("VS5").customerOrder(7).build());
        List<Product> finalProducts = productService.orderCustomerProcess(productOrders);
        assertThat(finalProducts.size()).isEqualTo(1);
    }

}
