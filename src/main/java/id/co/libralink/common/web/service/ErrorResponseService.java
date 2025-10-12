package id.co.libralink.common.web.service;

import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.web.model.ErrorResponse;
import id.co.libralink.common.web.model.FieldErrorMessage;
import org.springframework.http.HttpStatus;

public interface ErrorResponseService {

    FieldErrorMessage build(String field, ErrorCode code);

    ErrorResponse build(HttpStatus status, String defaultMessage);

    ErrorResponse build(HttpStatus status, ErrorCode code);

    ErrorResponse build(HttpStatus status, ErrorCode code, Object[] args);

    ErrorResponse build(HttpStatus status, ErrorCode code, String defaultMessage);

    ErrorResponse build(HttpStatus status, ErrorCode code, Object[] args, String defaultMessage);

}
