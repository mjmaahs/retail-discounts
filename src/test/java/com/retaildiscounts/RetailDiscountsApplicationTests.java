package com.retaildiscounts;

import com.retaildiscounts.exception.EntityNotFoundException;
import com.retaildiscounts.model.entity.*;
import com.retaildiscounts.repository.AppUserRepository;
import com.retaildiscounts.repository.CustomerRepository;
import com.retaildiscounts.repository.ProductRepository;
import com.retaildiscounts.service.AppUserService;
import com.retaildiscounts.service.DiscountCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RetailDiscountsApplicationTests {

	@Mock
	private AppUserRepository appUserRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private AppUserService appUserService;

	@InjectMocks
	private DiscountCalculatorService discountCalculatorService;

	@Test
	void contextLoads() {
	}

	@Test
	void testLoadUserByUsername() {
		AppUser mockAppUser = new AppUser();
		mockAppUser.setUsername("testUser");
		mockAppUser.setPassword("testPassword");
		mockAppUser.setRole("USER");

		when(appUserRepository.findByUsername("testUser")).thenReturn(mockAppUser);

		UserDetails userDetails = appUserService.loadUserByUsername("testUser");
		assertEquals("testUser", userDetails.getUsername());
		assertEquals("testPassword", userDetails.getPassword());
	}

	@Test
	void testLoadUserByUsernameNotFound() {
		when(appUserRepository.findByUsername("testUser")).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername("testUser"));
	}

	@Test
	void testCalculateNetAmountForEmployeeDiscount() {
		Customer mockCustomer = new Customer();
		mockCustomer.setUsername("testCustomer");
		mockCustomer.setType(CustomerType.EMPLOYEE);
		mockCustomer.setJoiningDate(LocalDate.now().minusYears(1));

		Product mockProduct1 = new Product();
		mockProduct1.setPrice(100);
		mockProduct1.setGrocery(false);

		Product mockProduct2 = new Product();
		mockProduct2.setPrice(200);
		mockProduct2.setGrocery(true);

		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.of(mockCustomer));
		when(productRepository.findProductBySerialNumber("1")).thenReturn(Optional.of(mockProduct1));
		when(productRepository.findProductBySerialNumber("2")).thenReturn(Optional.of(mockProduct2));

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("1", "2"));

		BigDecimal netAmount = discountCalculatorService.calculateNetAmount(bill);
		assertEquals(netAmount, new BigDecimal("255.000"));
	}

	@Test
	void testCalculateNetAmountForAffiliateDiscount() {
		Customer mockCustomer = new Customer();
		mockCustomer.setUsername("testCustomer");
		mockCustomer.setType(CustomerType.AFFILIATE);
		mockCustomer.setJoiningDate(LocalDate.now().minusYears(2));

		Product mockProduct1 = new Product();
		mockProduct1.setPrice(100);
		mockProduct1.setGrocery(false);

		Product mockProduct2 = new Product();
		mockProduct2.setPrice(200);
		mockProduct2.setGrocery(true);

		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.of(mockCustomer));
		when(productRepository.findProductBySerialNumber("1")).thenReturn(Optional.of(mockProduct1));
		when(productRepository.findProductBySerialNumber("2")).thenReturn(Optional.of(mockProduct2));

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("1", "2"));

		BigDecimal netAmount = discountCalculatorService.calculateNetAmount(bill);
		assertEquals(netAmount, new BigDecimal("275.000"));
	}

	@Test
	void testCalculateNetAmountForNewNormalCustomer() {
		Customer mockCustomer = new Customer();
		mockCustomer.setUsername("testCustomer");
		mockCustomer.setType(CustomerType.NORMAL);
		mockCustomer.setJoiningDate(LocalDate.now().minusYears(1));

		Product mockProduct1 = new Product();
		mockProduct1.setPrice(100);
		mockProduct1.setGrocery(false);

		Product mockProduct2 = new Product();
		mockProduct2.setPrice(200);
		mockProduct2.setGrocery(true);

		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.of(mockCustomer));
		when(productRepository.findProductBySerialNumber("1")).thenReturn(Optional.of(mockProduct1));
		when(productRepository.findProductBySerialNumber("2")).thenReturn(Optional.of(mockProduct2));

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("1", "2"));

		BigDecimal netAmount = discountCalculatorService.calculateNetAmount(bill);
		assertEquals(netAmount, new BigDecimal("285.00"));
	}

	@Test
	void testCalculateNetAmountForOldNormalCustomer() {
		Customer mockCustomer = new Customer();
		mockCustomer.setUsername("testCustomer");
		mockCustomer.setType(CustomerType.NORMAL);
		mockCustomer.setJoiningDate(LocalDate.now().minusYears(3));

		Product mockProduct1 = new Product();
		mockProduct1.setPrice(100);
		mockProduct1.setGrocery(false);

		Product mockProduct2 = new Product();
		mockProduct2.setPrice(200);
		mockProduct2.setGrocery(true);

		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.of(mockCustomer));
		when(productRepository.findProductBySerialNumber("1")).thenReturn(Optional.of(mockProduct1));
		when(productRepository.findProductBySerialNumber("2")).thenReturn(Optional.of(mockProduct2));

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("1", "2"));

		BigDecimal netAmount = discountCalculatorService.calculateNetAmount(bill);
		assertEquals(netAmount, new BigDecimal("280.000"));
	}

	@Test
	void testCalculateNetAmountUsernameNotFound() {
		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.empty());

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("1", "2"));

		assertThrows(EntityNotFoundException.class, () -> discountCalculatorService.calculateNetAmount(bill));

	}

	@Test
	void testCalculateNetAmountProductNotFound() {
		Customer mockCustomer = new Customer();
		mockCustomer.setUsername("testCustomer");
		mockCustomer.setType(CustomerType.EMPLOYEE);
		mockCustomer.setJoiningDate(LocalDate.now().minusYears(3));

		Product mockProduct1 = new Product();
		mockProduct1.setPrice(100);

		Product mockProduct2 = new Product();
		mockProduct2.setPrice(200);

		when(customerRepository.findCustomerByUsername("testCustomer")).thenReturn(Optional.of(mockCustomer));
		when(productRepository.findProductBySerialNumber("1")).thenReturn(Optional.of(mockProduct1));
		when(productRepository.findProductBySerialNumber("2")).thenReturn(Optional.of(mockProduct2));

		Bill bill = new Bill();
		bill.setUsername("testCustomer");
		bill.setProductSerialNumbers(Arrays.asList("3", "4"));

		assertThrows(EntityNotFoundException.class, () -> discountCalculatorService.calculateNetAmount(bill));

	}
}
