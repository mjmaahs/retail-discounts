package com.retaildiscounts.controller;

import com.retaildiscounts.model.Bill;
import com.retaildiscounts.service.DiscountCalculatorService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class RetailController {

    @Autowired
    private DiscountCalculatorService discountCalculatorService;

    @PostMapping("/calculate-net-amount")
    public ResponseEntity<BigDecimal> calculateNetAmount(@RequestBody @Valid @NotNull Bill bill) {
        BigDecimal netAmount = discountCalculatorService.calculateNetAmount(bill);
        return new ResponseEntity<>(netAmount, HttpStatus.OK);
    }

}

