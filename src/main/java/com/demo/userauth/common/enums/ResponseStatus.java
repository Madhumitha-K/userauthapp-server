package com.demo.userauth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response Status
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS("success"),
    FAILURE("failure");

    private final String status;

}
