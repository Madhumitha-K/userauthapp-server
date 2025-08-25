package com.demo.userauth.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User Dto
 */
@Getter
@Setter
public class UserDto {

    private String umEmlId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String umUsrPwd;

    private String umAlsNme;

    private String umFstNme;

    private String umLstNme;

    private boolean umUsrSts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String identity;

}
