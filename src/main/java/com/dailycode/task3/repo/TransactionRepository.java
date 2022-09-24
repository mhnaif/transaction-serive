package com.dailycode.task3.repo;

import com.dailycode.task3.entity.Customer;
import com.dailycode.task3.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {


    @Query("select t from Transaction t , Customer c where 1=1 and t.Customer.CustomerId = c.CustomerId and c.civilId = :civilId")
    List<Transaction> getAllTransactionsByCivilId(Long civilId);

    @Query("select t from Transaction t where 1=1 and t.creationDate between :dateFrom and :dateTo")
    List<Transaction> getAllTransactionsBetweenDateFromAndDateTo(Instant dateFrom,Instant dateTo);
}
