package org.pdm.ib.pmt.router.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "REFERENCE_ID")
    private Integer referenceId;


    public User(@NotNull String username,
                @NotNull String password,
                @NotNull Integer referenceId) {

        this.username = username;
        this.password = password;
        this.referenceId = referenceId;
    }

}
