package com.retaildiscounts.exception;

import lombok.Data;

@Data
public class DiscountCalculatorErrorResponse {

    private int errorCode;
    private String errorMessage;
    private long timeStamp;

}
