package org.pdm.ib.pmt.router.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.pdm.ib.pmt.router.enumz.CustType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {

    private static final long serialVersionUID = 2852515071912274053L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", updatable = false, insertable = false, unique = true)
    private Long id;

    @Column(name = "LAST_NAME", length = 35)
    @NotNull
    private String lastName;

    @Column(name = "FIRST_NAME", length = 35)
    @NotNull
    private String firstName;

    @Column(name = "BIRTH_DATE", updatable = false)
    @NotNull
    private Date birthDate;

    @Column(name = "SERIAL_NUMBER", updatable = false, length = 13, unique = true)
    @NotNull
    private String serialNumber;

    @Column(name = "CUSTOMER_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private CustType customerType;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    Customer() {

    }

    public Customer(@NotNull(message = "The constructor was called with null Last Name!") String lastName,
                    @NotNull(message = "The constructor was called with null First Name!") String firstName,
                    @NotNull(message = "The constructor was called with null Serial Number!") String serialNumber,
                    @NotNull(message = "The constructor was called with null BirthDate!") Date birthDate,
                    @NotNull(message = "The constructor was called with null CustomerType") CustType customerType) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.serialNumber = serialNumber;
        this.birthDate = birthDate;
        this.customerType = customerType;
    }

}
