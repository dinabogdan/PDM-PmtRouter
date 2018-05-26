package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.entities.Transaction;
import org.pdm.ib.pmt.router.entities.Tx;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.exceptions.TransactionNotFoundException;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.pdm.ib.pmt.router.repos.TransactionRepository;
import org.pdm.ib.pmt.router.repos.TxRepository;
import org.pdm.ib.pmt.router.utils.PDMRouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TransactionResource {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    private TxRepository txRepository;

    @Autowired
    private TransactionResource(
            @NotNull(message = "Customer Repository was not autowired in TransactionResource!")
                    CustomerRepository customerRepository,
            @NotNull(message = "Account Repository was not autowired in TransactionResource!")
                    AccountRepository accountRepository,
            @NotNull(message = "Transaction Repository was not autowired in TransactionResource!")
                    TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/customers/{customerId}/accountsByAcctId/{accountId}/transactions")
    public List<Transaction> getAllTransactionForASpecificAccountByAcctId(
            @PathVariable Long customerId,
            @PathVariable Long accountId) {
        log.debug("### Enter: getAllTransactionForASpecificAccountByAcctId() for customerId: "
                + customerId + " and accountId: " + accountId);

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account account = optionalAccount.orElseThrow(
                () -> new CustAccountNotFoundException(
                        "The account with id: " + accountId + " was not found!"
                ));

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(account.getPayers());
        allTransactions.addAll(account.getReceivers());
        return PDMRouterUtils.orderTransactions(allTransactions);
    }

    @GetMapping("/customers/{customerId}/accountsByAcctNo/{accountNumber}/transactions")
    public List<Transaction> getgetAllTransactionForASpecificAccountByAcctNo(
            @PathVariable Long customerId,
            @PathVariable Integer accountNumber) {
        log.debug("### Enter: getAllTransactionForASpecificAccountByAcctNo() for customerId: "
                + customerId + " and accountNumber: " + accountNumber);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        Account account = optionalAccount.orElseThrow(
                () -> new CustAccountNotFoundException(
                        "The account with id: " + accountNumber + " was not found!"
                ));

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(account.getPayers());
        allTransactions.addAll(account.getReceivers());
        return PDMRouterUtils.orderTransactions(allTransactions);
    }

    @GetMapping("/customers/{customerId}/accountsByAcctNo/{accountNumber}/transactions/{transactionId}")
    public Transaction getSpecificTransactionForAcctNo(
            @PathVariable Long customerId,
            @PathVariable Integer accountNumber,
            @PathVariable Long transactionId) {
        log.debug("### Enter: getSpecificTransactionForAcctNo() for customerId: "
                + customerId + " and accountNumber: "
                + accountNumber + " and transactionId: "
                + transactionId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException(
                        "The customer with id: " + customerId + " was not found!"
                ));

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        Account account = optionalAccount.orElseThrow(
                () -> new CustAccountNotFoundException(
                        "The account with id: " + accountNumber + " was not found!"
                ));

        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        optionalTransaction.orElseThrow(
                () -> new TransactionNotFoundException(
                        "The transaction with id: " + transactionId + " was not found!"
                ));

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(account.getPayers());
        transactions.addAll(account.getReceivers());
        return transactions.
                stream().
                filter(t -> t.getTxId().equals(transactionId)).
                findFirst().
                get();
    }

    @GetMapping("/transactions")
    public List<Tx> getTransactions() {
        List<Tx> txes = new ArrayList<>();
        Iterable<Tx> all = txRepository.findAll();
        all.forEach(t -> txes.add(t));
        return txes;
    }

    @PostMapping("/transactions")
    public void addTransactions(@RequestBody Tx tx) {
        tx.setNotified(false);
        txRepository.save(tx);
    }

}
