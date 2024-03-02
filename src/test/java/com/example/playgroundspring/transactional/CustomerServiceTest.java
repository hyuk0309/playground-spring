package com.example.playgroundspring.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void test() {
        Customer newCustomer = Customer.builder()
            .firstName("hello")
            .lastName("world")
            .build();

        Customer savedCustomer = customerService.saveCustomer(newCustomer);

        Long newCustomerId = savedCustomer.getId();
        Customer retrievedCustomer = customerService.getCustomer(newCustomerId);

        assertThat(retrievedCustomer.getId()).isEqualTo(savedCustomer.getId());
    }

    @Test
    void test2() {
        Customer newCustomer = Customer.builder()
            .firstName("hello")
            .lastName("world")
            .build();

        try {
            customerService.saveCustomerWithException(newCustomer);
        } catch (RuntimeException e) {
            System.out.println("예외 발생해 rollback 함.");
        }
        
        List<Customer> allCustomers = customerService.findAllCustomers();
        assertThat(allCustomers).isEmpty();
    }

}