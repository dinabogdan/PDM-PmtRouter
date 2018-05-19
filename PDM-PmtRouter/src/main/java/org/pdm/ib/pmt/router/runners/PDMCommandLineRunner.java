package org.pdm.ib.pmt.router.runners;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.command.AccountBalanceCommand;
import org.pdm.ib.pmt.router.entities.*;
import org.pdm.ib.pmt.router.enumz.AcctType;
import org.pdm.ib.pmt.router.enumz.CustType;
import org.pdm.ib.pmt.router.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Slf4j
@Component
public class PDMCommandLineRunner implements CommandLineRunner {

    @Autowired
    AccountRepository acctRepo;

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    TransactionRepository txRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AccountBalanceRepository accountBalanceRepo;

    @Override
    public void run(String... args) throws Exception {

        AccountBalance balance1 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(1450))
                .month(new GregorianCalendar(2017, 1, 1).getTime())
                .build();

        AccountBalance balance2 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(3356))
                .month(new GregorianCalendar(2017, 2, 1).getTime())
                .build();

        AccountBalance balance3 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(2256))
                .month(new GregorianCalendar(2017, 3, 1).getTime())
                .build();

        AccountBalance balance4 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(5556))
                .month(new GregorianCalendar(2017, 4, 1).getTime())
                .build();

        AccountBalance balance5 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(6556))
                .month(new GregorianCalendar(2017, 5, 1).getTime())
                .build();

        AccountBalance balance6 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(8956))
                .month(new GregorianCalendar(2017, 6, 1).getTime())
                .build();

        AccountBalance balance7 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(8400))
                .month(new GregorianCalendar(2017, 7, 1).getTime())
                .build();

        AccountBalance balance8 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(1200))
                .month(new GregorianCalendar(2017, 8, 1).getTime())
                .build();

        AccountBalance balance9 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(3450))
                .month(new GregorianCalendar(2017, 9, 1).getTime())
                .build();

        AccountBalance balance10 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(6500))
                .month(new GregorianCalendar(2017, 10, 1).getTime())
                .build();

        AccountBalance balance11 = AccountBalance
                .builder()
                .balance(BigDecimal.valueOf(9209))
                .month(new GregorianCalendar(2017, 11, 1).getTime())
                .build();

        accountBalanceRepo.save(balance1);
        accountBalanceRepo.save(balance2);
        accountBalanceRepo.save(balance3);
        accountBalanceRepo.save(balance4);
        accountBalanceRepo.save(balance5);
        accountBalanceRepo.save(balance6);
        accountBalanceRepo.save(balance7);
        accountBalanceRepo.save(balance8);
        accountBalanceRepo.save(balance9);
        accountBalanceRepo.save(balance10);
        accountBalanceRepo.save(balance11);

        User johnDoeUser = new User("john.doe", "foo", "ACCESS_TOKEN", 1);
        User freddyKruegerUser = new User("freddy.krueger", "bar", "ACCESS_TOKEN", 2);

        userRepo.save(johnDoeUser);
        userRepo.save(freddyKruegerUser);

        Date johnDoeBirthDate = new Date(new java.util.Date().getTime());
        Date freddyKruegerBirthDate = new Date(new java.util.Date().getTime());
        Customer johnDoe = new Customer(1L,
                "John",
                "Doe",
                johnDoeBirthDate,
                "1950503410039",
                CustType.INDIVIDUAL);

        Customer freddyKruger = new Customer(2L,
                "Freddy",
                "Krugger",
                freddyKruegerBirthDate,
                "1950503410040",
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
                AcctType.DEPOSIT_ACCOUNT,
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
