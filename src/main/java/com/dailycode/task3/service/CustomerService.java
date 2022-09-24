package com.dailycode.task3.service;


import com.dailycode.task3.entity.Customer;
import com.dailycode.task3.repo.CustomerRepository;
import com.dailycode.task3.request.CustomerRequest;
import com.dailycode.task3.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerResponse addCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setCustomerType(request.getCustomerType());
        customer.setBalance(request.getBalance());
        customer.setName(request.getName());
        customer.setCivilId(request.getCivilId());

        Customer saved = customerRepository.save(customer);

        return getResponse(saved);

    }

    public CustomerResponse getCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        return getResponse(customer);
    }

    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
        customerRepository.deleteById(customer.getCustomerId());
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        customer.setCustomerType(request.getCustomerType());
        customer.setBalance(request.getBalance());
        customer.setName(request.getName());
        customer.setCivilId(request.getCivilId());
        Customer saved = customerRepository.save(customer);

        return getResponse(saved);

    }

    private CustomerResponse getResponse(Customer saved) {
        CustomerResponse response = new CustomerResponse();
        response.setCustomerId(saved.getCustomerId());
        response.setCivilId(saved.getCivilId());
        response.setName(saved.getName());
        response.setBalance(saved.getBalance());
        return response;
    }
}
