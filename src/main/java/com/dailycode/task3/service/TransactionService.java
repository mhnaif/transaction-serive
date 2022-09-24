package com.dailycode.task3.service;


import com.dailycode.task3.entity.Customer;
import com.dailycode.task3.entity.Transaction;
import com.dailycode.task3.repo.CustomerRepository;
import com.dailycode.task3.repo.TransactionRepository;
import com.dailycode.task3.request.TransactionRequest;
import com.dailycode.task3.response.CustomerResponse;
import com.dailycode.task3.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionResponse addTransaction(TransactionRequest request) throws Exception {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        BigDecimal balance = customer.getBalance();
        BigDecimal amount = request.getAmount();
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("not allowed");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(request.getTransactionId());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setAccountFrom(request.getAccountFrom());
        transaction.setCreationDate(request.getCreationDate());
        transaction.setAccountTo(request.getAccountTo());
        transaction.setAmount(request.getAmount());
        transaction.setCustomer(customer);

        Transaction saved = transactionRepository.save(transaction);
        customer.setBalance(balance.subtract(amount));
        customerRepository.save(customer);
        Customer customer1 = saved.getCustomer();

        return getResponse(saved, customer1);
    }

    public List<TransactionResponse> getTransactionsByCivilId(Long civilId) {
        return transactionRepository.getAllTransactionsByCivilId(civilId)
                .stream()
                .map(e -> getResponse(e, e.getCustomer())).collect(Collectors.toList());

    }

    public List<TransactionResponse> getTransactionsBetween2dates(Instant dataFrom, Instant dateTo) {
        return transactionRepository.getAllTransactionsBetweenDateFromAndDateTo(dataFrom, dateTo)
                .stream()
                .map(e -> getResponse(e, e.getCustomer())).collect(Collectors.toList());
    }

    public TransactionResponse getTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        Customer customer = transaction.getCustomer();
        return getResponse(transaction,customer);
    }

    public void deleteTransaction(Long transactionId) {
        TransactionResponse transaction = getTransaction(transactionId);
        transactionRepository.deleteById(transaction.getTransactionId());
    }

    public TransactionResponse updateTransaction(Long transactionId, TransactionRequest request) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));



        transaction.setTransactionId(request.getTransactionId());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setAccountFrom(request.getAccountFrom());
        transaction.setCreationDate(request.getCreationDate());
        transaction.setAccountTo(request.getAccountTo());
        transaction.setAmount(request.getAmount());
        transaction.setCustomer(customer);

        Transaction saved = transactionRepository.save(transaction);

        TransactionResponse response = getResponse(saved, saved.getCustomer());

        return response;
    }


    private TransactionResponse getResponse(Transaction saved, Customer customer) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(saved.getTransactionId());
        response.setAmount(saved.getAmount());
        response.setAccountFrom(saved.getAccountFrom());
        response.setAccountTo(saved.getAccountTo());
        response.setCreationDate(saved.getCreationDate());
        response.setTransactionType(saved.getTransactionType());


        CustomerResponse customer1 = response.getCustomer();
        response.setCustomer(customer1);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerId(customer1.getCustomerId());
        customerResponse.setBalance(customer1.getBalance());
        customerResponse.setCivilId(customer1.getCivilId());
        customerResponse.setName(customer1.getName());
        return response;
    }
}
