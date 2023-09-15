package com.retaildiscounts.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "bills")
public class Bill {

    @Id
    private String id;
    @NotEmpty(message = "Product serial numbers should not be empty")
    private List<String> productSerialNumbers;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    private BigDecimal totalAmount;

    public Bill() {
    }

    public Bill(List<String> productSerialNumbers, String username, BigDecimal totalAmount) {
        this.productSerialNumbers = productSerialNumbers;
        this.username = username;
        this.totalAmount = totalAmount;
    }

}

