package id.co.libralink.common.web.controller;

import id.co.libralink.apigateway.security.enums.SecurityErrorCode;
import id.co.libralink.apigateway.security.exception.PasswordException;
import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.base.controller.BaseErrorHandler;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.web.model.ErrorResponse;
import id.co.libralink.common.web.model.FieldErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(1)
@ControllerAdvice
public class SecurityErrorHandler extends BaseErrorHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleAuthenticationException(AuthenticationException ex) {
        log.error("[RequestId : {}] Handle AuthenticationException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorCode errorCode = getAuthenticationCode(ex);
        return errorResponseService.build(HttpStatus.UNAUTHORIZED, errorCode, ex.getLocalizedMessage());
    }

    @ExceptionHandler(GeneralSecurityException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleGeneralSecurityException(GeneralSecurityException ex) {
        log.error("[RequestId : {}] Handle GeneralSecurityException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        return errorResponseService.build(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        log.error("[RequestId : {}] Handle AccessDeniedException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorCode errorCode = SecurityErrorCode.ACCESS_NOT_MATCH;
        return errorResponseService.build(HttpStatus.FORBIDDEN, errorCode, ex.getLocalizedMessage());
    }

    @ExceptionHandler(PasswordException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleChangePasswordException(PasswordException ex) {
        log.error("[RequestId : {}] Handle PasswordException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorResponse response = errorResponseService.build(HttpStatus.BAD_REQUEST, "invalid password");
        List<FieldErrorMessage> errors = ex.getErrorCodes().stream()
                .map(ec -> errorResponseService.build("password", ec))
                .collect(Collectors.toList());
        response.setErrors(errors);
        return response;
    }

    private ErrorCode getAuthenticationCode(AuthenticationException exception) {
        return switch (exception) {
            case LockedException ignored -> SecurityErrorCode.USER_IS_LOCKED;
            case DisabledException ignored -> SecurityErrorCode.USER_NOT_ACTIVE;
            case SessionAuthenticationException ignored -> SecurityErrorCode.SINGLE_DEVICE_SIGN_ON;
            case UsernameNotFoundException ignored -> SecurityErrorCode.USERNAME_NOT_FOUND;
            case AuthenticationCredentialsNotFoundException ignored -> SecurityErrorCode.USER_NOT_AUTHENTICATED;
            default -> SecurityErrorCode.INVALID_USERNAME_OR_PASSWORD;
        };
    }

}
