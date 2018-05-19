package org.pdm.ib.pmt.router.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.pdm.ib.pmt.router.entities.Customer;
import org.pdm.ib.pmt.router.enumz.AcctType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class AccountCommand implements Serializable {
    private Long id;
    private Integer accountNumber;
    private BigDecimal balance;
    private AcctType accountType;
    private Date openDate;
    private Customer customer;
    private List<TransactionCommand> payers;
    private List<TransactionCommand> receivers;
}
