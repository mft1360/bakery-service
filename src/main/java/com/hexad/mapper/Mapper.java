package com.hexad.mapper;

import com.hexad.dto.PackDTO;
import com.hexad.dto.ProductDTO;
import com.hexad.dto.ProductOrderDTO;
import com.hexad.entity.Pack;
import com.hexad.entity.Product;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author R.fatthi
 */
@org.mapstruct.Mapper
public interface Mapper {

    Product toProduct(ProductOrderDTO ProductOrderDTO);

    default List<Product> toProducts(List<ProductOrderDTO> ProductOrderDTOs) {
        return ProductOrderDTOs.stream()
                .map(productOrderDTO -> toProduct(productOrderDTO))
                .collect(Collectors.toList());
    }

    @Mapping(target = "packs", source = "packs")
    ProductDTO toProductDTO(Product product);

    default List<ProductDTO> toProductDTOs(List<Product> products) {
        return products.stream()
                .map(product -> toProductDTO(product))
                .collect(Collectors.toList());
    }

    PackDTO toPackDTO(Pack pack);

    default List<PackDTO> toPackDTOs(List<Pack> packs) {
        return packs.stream()
                .map(pack -> toPackDTO(pack))
                .collect(Collectors.toList());
    }

}
