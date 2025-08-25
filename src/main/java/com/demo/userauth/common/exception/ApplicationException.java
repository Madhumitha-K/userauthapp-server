package com.demo.userauth.common.exception;

import com.demo.userauth.common.enums.ResponseStatus;
import lombok.Getter;

/**
 * Application Exception
 */
@Getter
public class ApplicationException extends Exception{

    private final String responseCode;
    private final String responseStatus;
    private final String responseMessage;

    public ApplicationException(String responseCode, String responseMessage) {
        super(responseMessage);
        this.responseCode = responseCode;
        this.responseStatus = ResponseStatus.FAILURE.getStatus();
        this.responseMessage = responseMessage;
    }

}
