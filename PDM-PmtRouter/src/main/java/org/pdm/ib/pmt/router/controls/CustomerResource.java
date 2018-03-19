package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CustomerResource {

    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerResource(@NotNull(message = "Customer Repository was not autowired in CustomerResource!")
                                     CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
        return customer.orElseThrow(() -> new CustomerNotFoundException(
                "The customer with id: " + customerId + " was not found!"));
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> addNewCustomer(@Valid @RequestBody Customer customer) {
        log.debug("### Enter: addNewCustomer()");
        Customer savedCustomer = customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedCustomer.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

}
