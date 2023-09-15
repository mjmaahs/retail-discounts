package com.retaildiscounts.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String serialNumber;
    private String name;
    private boolean isGrocery;
    private double price;

    public Product() {
    }

    public Product(String serialNumber, String name, boolean isGrocery, double price) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.isGrocery = isGrocery;
        this.price = price;
    }

}
