package com.dailycode.task3.entity;


import com.dailycode.task3.constant.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "civilId")
    private Integer civilId;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "customer_type")
    private CustomerType customerType;
}
