package com.hexad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author R.fatthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDTO {

    @NotNull
    @ApiModelProperty(value = "product code")
    private String productCode;

    @NotNull
    @ApiModelProperty(value = "order number")
    private int customerOrder;

}
