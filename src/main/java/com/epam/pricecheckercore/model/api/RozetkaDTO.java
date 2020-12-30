package com.epam.pricecheckercore.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RozetkaDTO {

    @JsonProperty(value = "old_price")
    private int oldPrice;
    private int price;
    @JsonProperty(value = "sell_status")
    private String status;
}
