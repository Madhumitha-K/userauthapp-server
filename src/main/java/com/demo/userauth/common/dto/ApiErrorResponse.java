package com.demo.userauth.common.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Api Error Response
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private String responseCode;
    private String responseStatus;
    private String responseMessage;

}
