package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        log.debug("### Enter: getAllCustomers()");
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        log.debug("### Enter: getCustomerById() for id: " + customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);

        /*if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }*/

        return customer.orElseThrow(() -> new CustomerNotFoundException("The customer with id: " + customerId + " was not found!"));
    }

}
