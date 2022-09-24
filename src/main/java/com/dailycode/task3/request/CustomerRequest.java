package com.dailycode.task3.request;


import com.dailycode.task3.constant.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Long customerId;
    private String name;
    private Integer civilId;
    private BigDecimal balance;
    private CustomerType customerType;
}
