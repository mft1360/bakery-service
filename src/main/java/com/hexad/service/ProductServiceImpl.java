package com.hexad.service;

import com.hexad.entity.Pack;
import com.hexad.entity.Product;
import com.hexad.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author R.fatthi
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public JpaRepository<Product, Long> getGenericRepo() {
        return productRepository;
    }


    /**
     * calculate cost and breakdown of products and packs according customer order.
     * you can define product and packs full dynamic and it is not limit.
     * for example {6,8,15} or {1,4,9,15,20} and .....
     *
     * @param products
     */
    @Override
    public List<Product> orderCustomerProcess(List<Product> products) {
        List<String> productCodes = products.stream().map(product -> product.getProductCode()).collect(Collectors.toList());
        List<Product> fullProducts = productRepository.findByProductCodeIn(productCodes);
        fullProducts.forEach(product -> {
            product.setCustomerOrder(products.stream().filter(prd -> prd.getProductCode().equals(product.getProductCode())).findFirst().get().getCustomerOrder());
            int[] packs = product.getPacks().stream().mapToInt(pack -> pack.getCountPack()).toArray();
            List<String> resultOfBreakDownProcessPack = BreakDownProcess.breakDownProcessPack(product.getCustomerOrder(), packs);
            AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
            resultOfBreakDownProcessPack.stream().forEach(breakDown -> {
                Pack pack = product.getPacks().stream().filter(pck -> pck.getCountPack() == Integer.parseInt(breakDown.split("x")[1])).findFirst().get();
                totalPrice.updateAndGet(v -> new Double((double) (v + pack.getPricePack() * Integer.parseInt(breakDown.split("x")[0]))));
                pack.setCountProcess(Integer.parseInt(breakDown.split("x")[0]));
            });
            product.setTotalPrice(new BigDecimal(totalPrice.get()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            product.setPacks(product.getPacks().stream().filter(pak -> pak.getCountProcess() > 0)
                    .sorted(Comparator.comparingInt(Pack::getCountPack).reversed())
                    .collect(Collectors.toList()));
        });
        return fullProducts;
    }
}
