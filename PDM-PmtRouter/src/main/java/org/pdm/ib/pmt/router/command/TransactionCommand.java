package org.pdm.ib.pmt.router.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@ToString
@EqualsAndHashCode
public class TransactionCommand {
    private Long txId;
    private AccountCommand payerAccount;
    private AccountCommand receiverAccount;
    private BigDecimal sum;
    private Date processDate;
}
