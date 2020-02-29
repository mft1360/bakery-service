package com.hexad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author R.fatthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @ApiModelProperty(value = "product code")
    private String productCode;

    @ApiModelProperty(value = "product name")
    private String productName;

    @ApiModelProperty(value = "order")
    private int customerOrder;

    @ApiModelProperty(value = "total Price")
    private double totalPrice;

    List<PackDTO> packs;

}
