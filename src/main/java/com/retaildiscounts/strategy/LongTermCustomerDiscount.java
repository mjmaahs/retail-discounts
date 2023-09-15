package com.retaildiscounts.strategy;

import com.retaildiscounts.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class LongTermCustomerDiscount implements DiscountStrategy {

    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.05");  // 5%

    @Override
    public BigDecimal applyDiscount(BigDecimal totalAmount, List<Product> products) {
        BigDecimal discountableAmount = calculateDiscountableAmount(totalAmount, products);
        return discountableAmount.multiply(DISCOUNT_RATE);
    }

    private BigDecimal calculateDiscountableAmount(BigDecimal totalAmount, List<Product> products) {
        BigDecimal totalGroceryAmount = BigDecimal.ZERO;
        for (Product temp: products){
            if(temp.isGrocery()){
                totalGroceryAmount = totalGroceryAmount.add(BigDecimal.valueOf(temp.getPrice()));
            }
        }
        return totalAmount.subtract(totalGroceryAmount);
    }

}
