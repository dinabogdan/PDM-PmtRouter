package org.pdm.ib.pmt.router.runners;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Account;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.entities.Transaction;
import org.pdm.ib.pmt.router.enumz.AcctType;
import org.pdm.ib.pmt.router.enumz.CustType;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.pdm.ib.pmt.router.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Slf4j
@Component
public class PDMCommandLineRunner implements CommandLineRunner {

    @Autowired
    AccountRepository acctRepo;

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    TransactionRepository txRepo;

    @Override
    public void run(String... args) throws Exception {
        Date johnDoeBirthDate = new Date(new java.util.Date().getTime());
        Date freddyKruegerBirthDate = new Date(new java.util.Date().getTime());
        Customer johnDoe = new Customer(
                "John",
                "Doe",
                "1950503410039",
                johnDoeBirthDate,
                CustType.INDIVIDUAL);

        Customer freddyKruger = new Customer(
                "Freddy",
                "Krugger",
                "1950503410040",
                freddyKruegerBirthDate,
                CustType.INDIVIDUAL);

        custRepo.save(johnDoe);
        custRepo.save(freddyKruger);

        Account johnDoeAcct1 = new Account(
                123456,
                new BigDecimal(23),
                AcctType.CURRENT_ACCOUNT,
                new Date(new java.util.Date().getTime()),
                johnDoe);

        Account johnDoeAcct2 = new Account(
                123457,
                new BigDecimal(1000),
                AcctType.CURRENT_ACCOUNT,
                new Date(new java.util.Date().getTime()),
                johnDoe);

        Account freddyKrugerAcct1 = new Account(
                123458,
                new BigDecimal(1200),
                AcctType.CURRENT_ACCOUNT,
                new Date(new java.util.Date().getTime()),
                freddyKruger);

        acctRepo.save(johnDoeAcct1);
        acctRepo.save(johnDoeAcct2);
        acctRepo.save(freddyKrugerAcct1);

        Transaction tx1 = new Transaction(
                new BigDecimal(200),
                new Date(new java.util.Date().getTime()),
                johnDoeAcct1,
                freddyKrugerAcct1);

        txRepo.save(tx1);
    }
}
