package com.retaildiscounts.strategy;

import com.retaildiscounts.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class NoSpecialDiscount implements DiscountStrategy {

    @Override
    public BigDecimal applyDiscount(BigDecimal amount, List<Product> products) {
        return BigDecimal.ZERO;
    }

}
