package com.retaildiscounts.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String username;
    private CustomerType type;
    private LocalDate joiningDate;

    public Customer() {
    }

    public Customer(String username, CustomerType type, LocalDate joiningDate) {
        this.username = username;
        this.type = type;
        this.joiningDate = joiningDate;
    }

}
