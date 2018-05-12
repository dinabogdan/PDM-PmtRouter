package org.pdm.ib.pmt.router.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCommand {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
}
