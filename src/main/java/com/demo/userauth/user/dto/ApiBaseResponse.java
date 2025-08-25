package com.demo.userauth.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Api Base Response
 */
@Getter
@Setter
public class ApiBaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userIdentity;

    private String responseCode;

    private String responseMessage;

    private String responseStatus;

}
