package org.pdm.ib.pmt.router.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TX")
public class Tx {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id")
    private Long id;
    private String name;
    private BigDecimal amount;
    private Boolean notified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
}
