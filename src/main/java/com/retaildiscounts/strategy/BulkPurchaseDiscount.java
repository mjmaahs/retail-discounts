package com.retaildiscounts.strategy;

import com.retaildiscounts.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BulkPurchaseDiscount implements DiscountStrategy {

    private static final BigDecimal DISCOUNT_PER_HUNDRED = new BigDecimal("5");
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    @Override
    public BigDecimal applyDiscount(BigDecimal totalAmount, List<Product> products) {
        BigDecimal numberOfHundreds = totalAmount.divide(HUNDRED, 0, RoundingMode.FLOOR);
        return numberOfHundreds.multiply(DISCOUNT_PER_HUNDRED);
    }

}
