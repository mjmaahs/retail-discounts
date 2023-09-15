package com.retaildiscounts;

import com.retaildiscounts.model.AppUser;
import com.retaildiscounts.model.Customer;
import com.retaildiscounts.model.CustomerType;
import com.retaildiscounts.model.Product;
import com.retaildiscounts.repository.AppUserRepository;
import com.retaildiscounts.repository.CustomerRepository;
import com.retaildiscounts.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class RetailDiscountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailDiscountsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            AppUserRepository appUserRepository) {

        return args -> {

            // Deleting existing data
            productRepository.deleteAll();
            customerRepository.deleteAll();
            appUserRepository.deleteAll();

            // Inserting Products
            Product rice = new Product("000001", "Rice", true, 10.50d);
            Product milk = new Product("000002", "Milk", true, 7.75d);
            Product iPhone = new Product("000003", "iPhone", false, 150.99d);
            Product hammer = new Product("000004", "Hammer", false, 35.65d);
            Product tv = new Product("000005", "TV", false, 350.65d);

            productRepository.insert(rice);
            productRepository.insert(milk);
            productRepository.insert(iPhone);
            productRepository.insert(hammer);
            productRepository.insert(tv);

            // Inserting Customers
            Customer bruce = new Customer("bruce", CustomerType.EMPLOYEE, LocalDate.now().minusYears(5));
            Customer clark = new Customer("clark", CustomerType.AFFILIATE, LocalDate.now().minusYears(1));
            Customer barry = new Customer("barry", CustomerType.NORMAL, LocalDate.now());
            Customer lex = new Customer("lex", CustomerType.NORMAL, LocalDate.now().minusYears(2));

            customerRepository.insert(bruce);
            customerRepository.insert(clark);
            customerRepository.insert(barry);
            customerRepository.insert(lex);

            // Inserting AppUsers
            AppUser john = new AppUser("john", "{noop}test123", "CASHIER");
            AppUser mary = new AppUser("mary", "{noop}test123", "MANAGER");
            AppUser susan = new AppUser("susan", "{noop}test123", "CASHIER,MANAGER");

            appUserRepository.insert(john);
            appUserRepository.insert(mary);
            appUserRepository.insert(susan);

        };
    }

}
