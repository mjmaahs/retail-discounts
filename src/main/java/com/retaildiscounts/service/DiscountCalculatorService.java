package com.retaildiscounts.service;

import com.retaildiscounts.exception.EntityNotFoundException;
import com.retaildiscounts.model.Bill;
import com.retaildiscounts.model.Customer;
import com.retaildiscounts.model.CustomerType;
import com.retaildiscounts.model.Product;
import com.retaildiscounts.repository.CustomerRepository;
import com.retaildiscounts.repository.ProductRepository;
import com.retaildiscounts.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountCalculatorService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public BigDecimal calculateNetAmount(Bill bill) {

        // validate customer username and get it from DB
        Customer customer = getCustomer(bill);
        // validate products serial numbers and get it from DB
        List<Product> products = getProducts(bill);

        // Calculate total amount for all products
        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Determine which discount strategy to use
        DiscountStrategy discountStrategy = getDiscountStrategy(customer);

        // Calculate discount amount
        BigDecimal discountAmount = discountStrategy.applyDiscount(totalAmount, products);
        System.out.println("discountAmount: " + discountAmount);
        // Calculate bulk discount ($5 for every $100)
        BigDecimal bulkDiscount = new BulkPurchaseDiscount().applyDiscount(totalAmount, products);
        System.out.println("bulkDiscount: " + bulkDiscount);
        // Calculate net amount
        BigDecimal netAmount = totalAmount.subtract(discountAmount).subtract(bulkDiscount);
        System.out.println("netAmount: " + netAmount);
        return netAmount;
    }

    private DiscountStrategy getDiscountStrategy(Customer customer) {
        if (CustomerType.EMPLOYEE == customer.getType()) {
            return new EmployeeDiscount();
        } else if (CustomerType.AFFILIATE == customer.getType()) {
            return new AffiliateDiscount();
        } else if (ChronoUnit.YEARS.between(customer.getJoiningDate(), LocalDate.now()) > 2) {
            return new LongTermCustomerDiscount();
        } else {
            return new NoSpecialDiscount();
        }
    }

    private List<Product> getProducts(Bill bill) {
        List<Product> products = new ArrayList<>();
        for (String productSerialNumber : bill.getProductSerialNumbers()) {
            Optional<Product> productOptional = productRepository.findProductBySerialNumber(productSerialNumber);
            if (productOptional.isPresent()) {
                products.add(productOptional.get());
            } else {
                throw new EntityNotFoundException("product for the below serial number not found: " + productSerialNumber);
            }
        }
        return products;
    }

    private Customer getCustomer(Bill bill) {
        Customer customer;
        Optional<Customer> dbCustomerOptional = customerRepository.findCustomerByUsername(bill.getUsername());
        if (dbCustomerOptional.isPresent()) {
            customer = dbCustomerOptional.get();
        } else {
            throw new EntityNotFoundException("user for the below username not found: " + bill.getUsername());
        }
        return customer;
    }

}

