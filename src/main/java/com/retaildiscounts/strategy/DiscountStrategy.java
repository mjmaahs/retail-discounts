package com.retaildiscounts.strategy;

import com.retaildiscounts.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy {

    BigDecimal applyDiscount(BigDecimal amount, List<Product> products);

}

