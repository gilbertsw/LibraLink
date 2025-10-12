package id.co.libralink.common.web.controller;

import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.base.controller.BaseErrorHandler;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.exception.BaseException;
import id.co.libralink.common.enums.CommonErrorCode;
import id.co.libralink.common.exception.DataNotFoundException;
import id.co.libralink.common.web.model.ErrorResponse;
import id.co.libralink.common.web.model.FieldErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Order()
@ControllerAdvice
public class DefaultErrorHandler extends BaseErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGeneralException(Exception ex) {
        log.error("[RequestId : {}] Handle GeneralException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        return errorResponseService.build(HttpStatus.INTERNAL_SERVER_ERROR, CommonErrorCode.UNEXPECTED_ERROR_OCCURRED);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        log.error("[RequestId : {}] Handle MethodArgumentNotValidException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorResponse res = errorResponseService.build(HttpStatus.BAD_REQUEST, CommonErrorCode.PARAMETER_INVALID);
        List<FieldErrorMessage> errors = List.of(new FieldErrorMessage(ex.getRequestPartName(), ex.getMessage()));
        res.setErrors(errors);

        return res;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[RequestId : {}] Handle MethodArgumentNotValidException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorResponse res = errorResponseService.build(HttpStatus.BAD_REQUEST, CommonErrorCode.PARAMETER_INVALID);
        List<FieldErrorMessage> errors = ex.getFieldErrors().stream()
                .map(fe -> new FieldErrorMessage(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        res.setErrors(errors);

        return res;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBaseException(BaseException ex) {
        log.error("[RequestId : {}] Handle BaseException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        return errorResponseService.build(HttpStatus.BAD_REQUEST, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleDataNotFoundException(DataNotFoundException ex) {
        log.error("[RequestId : {}] Handle DataNotFoundException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        return errorResponseService.build(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getArgs(), ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleSQLException(SQLException ex) {
        log.error("[RequestId : {}] Handle SQLException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        String message = String.format("SQLState: %s, ErrorCode: %s, message: %s",
                ex.getSQLState(), ex.getErrorCode(), ex.getLocalizedMessage());
        return errorResponseService.build(HttpStatus.CONFLICT, message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("[RequestId : {}] Handle DataIntegrityViolationException: {}", MDC.get(MDCKey.REQUEST_ID.name()), ex.getMessage(), ex);
        ErrorCode errorCode = getIntegrityViolationCode(ex);
        return errorResponseService.build(HttpStatus.CONFLICT, errorCode, ex.getLocalizedMessage());
    }

}
