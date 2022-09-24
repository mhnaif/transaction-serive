package com.dailycode.task3.response;


import com.dailycode.task3.constant.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String name;
    private Integer civilId;
    private BigDecimal balance;
    private CustomerType customerType;
}
