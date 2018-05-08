package org.pdm.ib.pmt.router.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.pdm.ib.pmt.router.enumz.CustType;

import java.util.Date;
import java.util.List;

@Builder
@EqualsAndHashCode
@ToString
public class CustomerCommand {
    private Long id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String serialNumber;
    private CustType customerType;
    private List<AccountCommand> accounts;
}
