package com.example.playgroundspring.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer saveCustomerWithException(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        if (Objects.nonNull(savedCustomer.getId())) {
            throw new RuntimeException();
        }

        return savedCustomer;
    }

    public Customer getCustomer(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.orElse(null);
    }

    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        customerRepository.findAll()
            .forEach(customers::add);

        return customers;
    }
}
