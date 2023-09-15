package com.retaildiscounts.repository;

import com.retaildiscounts.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByUsername(String username);

}
