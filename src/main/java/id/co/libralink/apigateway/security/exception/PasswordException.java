package id.co.libralink.apigateway.security.exception;

import id.co.libralink.common.base.code.ErrorCode;

import java.util.List;

public class PasswordException extends RuntimeException {

    private final List<ErrorCode> errorCodes;

    public PasswordException(String message, List<ErrorCode> errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public List<ErrorCode> getErrorCodes() {
        return errorCodes;
    }

}
