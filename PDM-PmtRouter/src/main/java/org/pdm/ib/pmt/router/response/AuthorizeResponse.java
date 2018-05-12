package org.pdm.ib.pmt.router.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizeResponse {

    @JsonProperty
    private Boolean isAuthorized;
    @JsonProperty
    private Integer referenceId;
    @JsonProperty
    private String username;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String accessToken;
}
