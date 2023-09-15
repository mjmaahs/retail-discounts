package com.retaildiscounts.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BillDTO {

    @NotEmpty(message = "Product serial numbers should not be empty")
    private List<String> productSerialNumbers;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    private BigDecimal totalAmount;

}
