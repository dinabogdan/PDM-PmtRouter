package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.command.AccountBalanceCommand;
import org.pdm.ib.pmt.router.command.AccountCommand;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.AccountBalance;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.repos.AccountBalanceRepository;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AccountResource {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private AccountResource(
            @NotNull(message = "Customer Repository was not autowired in AccountResource!")
                    CustomerRepository customerRepository,
            @NotNull(message = "Account Repository was not autowired in AccountResource!")
                    AccountRepository accountRepository,
            @NotNull(message = "Account Balance Repository was not autowired in AccountResource!")
                    AccountBalanceRepository accountBalanceRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.accountBalanceRepository = accountBalanceRepository;
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

    @GetMapping("/balances")
    public List<AccountBalanceCommand> getLastYearBalance() {
        Iterable<AccountBalance> all = accountBalanceRepository.findAll();
        List<AccountBalanceCommand> accountBalances = new ArrayList<>();
        all.forEach(a -> {
            AccountBalanceCommand accountBalanceCommand = AccountBalanceCommand.builder()
                    .amount(a.getBalance())
                    .month(a.getMonth())
                    .build();
            accountBalances.add(accountBalanceCommand);

        });
        return accountBalances;
    }

    @PostMapping("/update-balance/{transaction}")
    public void updateBalance(@PathVariable BigDecimal transaction, @RequestBody AccountCommand accountCommand) {
        System.out.println("A intrat");
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountCommand.getAccountNumber());
        Account account = null;
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new CustAccountNotFoundException("The account with number " + accountCommand.getAccountNumber() + " was not found!");
        }

        BigDecimal actualBalance = account.getBalance();
        actualBalance = actualBalance.subtract(transaction);
        account.setBalance(actualBalance);
        accountRepository.save(account);
    }
}
