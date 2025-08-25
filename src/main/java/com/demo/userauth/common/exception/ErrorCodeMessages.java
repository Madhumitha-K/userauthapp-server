package com.demo.userauth.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error Codes & Messages
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeMessages {

    INVALID_REQUEST("EC-100", "Invalid Request"),
    USER_NOT_FOUND("EC-1000", "User not found"),
    USER_IDNTY_MANDATORY_ERROR("EC-1002", "User Identity is required and cannot be null or empty"),
    EMAIL_MANDATORY_ERROR("EC-1003", "Email ID is required and cannot be null or empty"),
    USER_INACTIVE_ERROR("EC-1004", "Inactive User"),
    INVALID_CREDENTIAL("EC-1005", "Invalid Username or Password"),
    PASSWORD_MANDATORY_ERROR("EC-1006", "Password is required and cannot be null or empty"),
    ALIASNAME_MANDATORY_ERROR("EC-1007", "Alias Name is required and cannot be null or empty"),
    FIRSTNAME_MANDATORY_ERROR("EC-1008", "First Name is required and cannot be null or empty"),
    LASTNAME_MANDATORY_ERROR("EC-1009", "Last Name is required and cannot be null or empty"),
    USER_ALREADY_EXIST_ERROR("EC-1010", "User already exists");


    private final String code;
    private final String message;

}
