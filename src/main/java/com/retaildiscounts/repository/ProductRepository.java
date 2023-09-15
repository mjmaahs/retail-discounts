package com.retaildiscounts.repository;

import com.retaildiscounts.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductBySerialNumber(String serialNumber);

}
