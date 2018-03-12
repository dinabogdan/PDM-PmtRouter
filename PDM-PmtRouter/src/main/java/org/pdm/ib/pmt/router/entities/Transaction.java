package org.pdm.ib.pmt.router.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.pdm.ib.pmt.router.converters.BigDecimalConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@EqualsAndHashCode
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TX_ID", updatable = false)
    private Integer txId;

    @Column(name = "DEBIT_ACCT", length = 10)
    private Integer debitAccount;

    @Column(name = "CREDIT_ACCT", length = 10)
    private Integer creditAccount;

    @Column(name = "SUM")
    @Convert(converter = BigDecimalConverter.class)
    private BigDecimal sum;

    @Column(name = "PROCESS_DATE")
    private Date processDate;

    Transaction() {

    }

    public Transaction(@NotNull(message = "The constructor was called with null DebitAccount!") Integer debitAccount,
                       @NotNull(message = "The constructor was called with null CreditAccount!") Integer creditAccount,
                       @NotNull(message = "The constructor was called with null Sum!") BigDecimal sum,
                       @NotNull(message = "The constructor was called with null ProcessDate!") Date processDate) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.sum = sum;
        this.processDate = processDate;
    }
}
