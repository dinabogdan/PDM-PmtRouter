package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AccountResource {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountResource(
            @NotNull(message = "Customer Repository was not autowired in AccountResource!")
                    CustomerRepository customerRepository,
            @NotNull(message = "Account Repository was not autowired in AccountResource!")
                    AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }


    @GetMapping("/customers/{customerId}/accounts")
    public List<Account> getAllUsersAccounts(@PathVariable Long customerId) {
        log.debug("### Enter: getAllUsersAccounts() for customerId: " + customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));
        return customer.getAccounts();
    }

    @GetMapping("/customers/{customerId}/accountsByAcctId/{accountId}")
    public Account getAccountById(@PathVariable Long customerId, @PathVariable Long accountId) {
        log.debug("### Enter: getAccountById() for customerId: " + customerId + " and accountId: " + accountId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        optionalAccount.orElseThrow(
                () -> new CustAccountNotFoundException(
                        "The account with id: " + accountId + " was not found!"
                ));
        List<Account> accounts = customer.getAccounts();
        for (Account acct : accounts) {
            if (acct.getId().equals(accountId)) {
                return acct;
            }
        }
        return null;
    }

    @GetMapping("/customers/{customerId}/accountsByAcctNo/{accountNumber}")
    public Account getAccountByAcctNo(@PathVariable Long customerId, @PathVariable Integer accountNumber) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        optionalAccount.orElseThrow(
                () -> new CustAccountNotFoundException(
                        "The account with number: " + accountNumber + " was not found!"
                ));
        List<Account> accounts = customer.getAccounts();
        for (Account acct : accounts) {
            if (acct.getAccountNumber().equals(accountNumber)) {
                return acct;
            }
        }
        return null;
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> addNewAccount(@PathVariable Long customerId,
                                                @Valid @RequestBody Account account) {
        log.debug("### Enter: addNewAccount() for customerId: " + customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedAccount.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

}
