package org.pdm.ib.pmt.router.controls;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.entities.Transaction;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.exceptions.TransactionNotFoundException;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.pdm.ib.pmt.router.repos.TransactionRepository;
import org.pdm.ib.pmt.router.utils.PDMRouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TransactionResource {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/customers/{customerId}/accountsByAcctId/{accountId}/transactions")
    public List<Transaction> getAllTransactionForASpecificAccountByAcctId(
            @PathVariable Long customerId,
            @PathVariable Long accountId) {
        log.debug("### Enter: getAllTransactionForASpecificAccountByAcctId() for customerId: "
                + customerId + " and accountId: " + accountId);

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent()) {
            throw new CustAccountNotFoundException("The account with id: " + accountId + " was not found!");
        }

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(account.get().getPayers());
        allTransactions.addAll(account.get().getReceivers());
        List<Transaction> orderedTransactions = PDMRouterUtils.orderTransactions(allTransactions);
        return orderedTransactions;
    }

    @GetMapping("/customers/{customerId}/accountsByAcctNo/{accountNumber}/transactions")
    public List<Transaction> getgetAllTransactionForASpecificAccountByAcctNo(
            @PathVariable Long customerId,
            @PathVariable Integer accountNumber) {
        log.debug("### Enter: getAllTransactionForASpecificAccountByAcctNo() for customerId: "
                + customerId + " and accountNumber: " + accountNumber);

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (!account.isPresent()) {
            throw new CustAccountNotFoundException("The account with id: " + accountNumber + " was not found!");
        }

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(account.get().getPayers());
        allTransactions.addAll(account.get().getReceivers());
        List<Transaction> orderedTransactions = PDMRouterUtils.orderTransactions(allTransactions);
        return orderedTransactions;
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
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("The customer with id: " + customerId + " was not found!");
        }

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (!account.isPresent()) {
            throw new CustAccountNotFoundException("The account with number: " + accountNumber + " was not found!");
        }

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (!transaction.isPresent()) {
            throw new TransactionNotFoundException("The transaction with id: " + transactionId + " was not found!");
        }

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(account.get().getPayers());
        transactions.addAll(account.get().getReceivers());
        Transaction txToReturn = transactions.
                stream().
                filter(t -> t.getTxId().equals(transactionId)).
                findFirst().
                get();

        return txToReturn;
    }

}
