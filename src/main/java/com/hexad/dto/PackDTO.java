package com.hexad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author R.fatthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackDTO {

    @ApiModelProperty(value = "number of pack")
    private int countProcess;

    @ApiModelProperty(value = "count of pack")
    private int countPack;

    @ApiModelProperty(value = "price of pack")
    private double pricePack;


}
