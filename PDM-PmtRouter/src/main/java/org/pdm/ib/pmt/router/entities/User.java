package org.pdm.ib.pmt.router.entities;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ACCESS_TOKEN")
    private String accessToken;
    @Column(name = "REFERENCE_ID")
    private Integer referenceId;

    User() {

    }

    public User(@NotNull String username,
                @NotNull String password,
                @NotNull String accessToken,
                @NotNull Integer referenceId) {

        this.username = username;
        this.password = password;
        this.referenceId = referenceId;
        this.accessToken = accessToken;
    }

}
