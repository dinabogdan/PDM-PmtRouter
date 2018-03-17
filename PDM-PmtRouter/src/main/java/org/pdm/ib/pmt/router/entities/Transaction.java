package org.pdm.ib.pmt.router.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TX_ID", updatable = false,  insertable = false, unique = true)
    private Long txId;

    @ManyToOne
    @JoinColumn(name = "PAYER_ACCT_NO", updatable = false)
    private Account payerAccount;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ACCT_NO", updatable = false)
    private Account receiverAccount;

    @Column(name = "SUM", updatable = false)
    @Convert(converter = BigDecimalConverter.class)
    private BigDecimal sum;

    @Column(name = "PROCESS_DATE", updatable = false)
    private Date processDate;

    Transaction() {

    }

    public Transaction(@NotNull(message = "The constructor was called with null Sum!") BigDecimal sum,
                       @NotNull(message = "The constructor was called with null ProcessDate!") Date processDate,
                       @NotNull(message = "The constructor was called with null PayerAccount reference!") Account payerAccount,
                       @NotNull(message = "The constructor was called with null ReceiverAccount reference!") Account receiverAccount) {
        this.sum = sum;
        this.processDate = processDate;
        this.payerAccount = payerAccount;
        this.receiverAccount = receiverAccount;
    }
}
