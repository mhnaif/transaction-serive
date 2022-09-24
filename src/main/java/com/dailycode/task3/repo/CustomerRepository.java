package com.dailycode.task3.repo;

import com.dailycode.task3.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
