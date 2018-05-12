package org.pdm.ib.pmt.router.entities;

import lombok.*;
import org.pdm.ib.pmt.router.converters.BigDecimalConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Builder
public class AccountBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DATE")
    private Date month;

    @Column(name = "BALANCE")
    @Convert(converter = BigDecimalConverter.class)
    private BigDecimal balance;

    AccountBalance() {

    }

    public AccountBalance(Date month,
                          BigDecimal balance) {
        this.month = month;
        this.balance = balance;
    }

    private AccountBalance(Long id,
                           Date month,
                           BigDecimal balance) {
        this.balance = balance;
        this.id = id;
        this.month = month;
    }

}
