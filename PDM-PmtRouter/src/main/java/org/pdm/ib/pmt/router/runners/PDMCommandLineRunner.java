package org.pdm.ib.pmt.router.runners;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.enumz.CustType;
import org.pdm.ib.pmt.router.repos.AccountRepository;
import org.pdm.ib.pmt.router.repos.CustomerRepository;
import org.pdm.ib.pmt.router.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Date birthDate = new Date(new java.util.Date().getTime());
        custRepo.save(new Customer("Dina", "Bogdan", "1950503410039", birthDate, CustType.INDIVIDUAL));

    }
}
