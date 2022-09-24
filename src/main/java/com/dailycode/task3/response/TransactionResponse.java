package com.dailycode.task3.response;


import com.dailycode.task3.constant.TransactionType;
import com.dailycode.task3.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long transactionId;
    private BigDecimal amount;
    private String accountFrom;
    private String accountTo;
    private Instant creationDate;
    private TransactionType transactionType;
    private CustomerResponse customer;



}
