package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AccountResource {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/customers/{customerId}/accounts")
    public List<Account> getAllUsersAccounts(@PathVariable Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        List<Account> accounts = customer.get().getAccounts();
        return accounts;
    }

    @GetMapping("/customers/{customerId}/accountsByAcctId/{accountId}")
    public Account getAccountById(@PathVariable Long customerId, @PathVariable Long accountId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            throw new CustAccountNotFoundException("The account with id: " + accountId + " was not found!");
        }

        List<Account> accounts = customer.get().getAccounts();
        for (Account acct : accounts) {
            if (acct.getId().equals(accountId)) {
                return acct;
            }
        }

        return null;
    }

    @GetMapping("/customers/{customerId}/accountsByAcctNo/{accountNumber}")
    public Account getAccountByAcctNo(@PathVariable Long customerId, @PathVariable Integer accountNumber) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (!account.isPresent()) {
            throw new CustAccountNotFoundException("The account with number: " + accountNumber + " was not found!");
        }

        List<Account> accounts = customer.get().getAccounts();
        for (Account acct : accounts) {
            if (acct.getAccountNumber().equals(accountNumber)) {
                return acct;
            }
        }

        return null;
    }

}
