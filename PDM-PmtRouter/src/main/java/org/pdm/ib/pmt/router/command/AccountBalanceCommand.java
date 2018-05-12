package org.pdm.ib.pmt.router.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public class AccountBalanceCommand {

    @JsonProperty
    private Date month;
    @JsonProperty
    private BigDecimal amount;

}
