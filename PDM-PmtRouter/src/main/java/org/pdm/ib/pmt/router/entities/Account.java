package org.pdm.ib.pmt.router.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.pdm.ib.pmt.router.converters.BigDecimalConverter;
import org.pdm.ib.pmt.router.enumz.AcctType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "ACCOUNTS")
@Getter
@ToString
@EqualsAndHashCode
public class Account implements Serializable {

    private static final long serialVersionUID = -7609771741668114770L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ACCT_ID", updatable = false)
    private Integer id;

    @Column(name = "ACCT_NO", updatable = false, length = 10, unique = true)
    private Integer accountNumber;

    @Column(name = "BALANCE")
    @Convert(converter = BigDecimalConverter.class)
    private BigDecimal balance;

    @Column(name = "ACCT_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private AcctType accountType;

    @Column(name = "OPEN_DATE", updatable = false)
    private Date openDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(mappedBy = "payerAccount", fetch = FetchType.LAZY)
    private Collection<Transaction> payers;

    @OneToMany(mappedBy = "receiverAccount", fetch = FetchType.LAZY)
    private Collection<Transaction> receivers;

    Account() {

    }

    public Account(@NotNull(message = "The constructor was called with null AccountNumber!") Integer accountNumber,
                   @NotNull(message = "The constructor was called with null Balance!") BigDecimal balance,
                   @NotNull(message = "The constructor was called with null AccountType!") AcctType accountType,
                   @NotNull(message = "The constructor was called with null OpenDate!") Date openDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.openDate = openDate;
    }

}
