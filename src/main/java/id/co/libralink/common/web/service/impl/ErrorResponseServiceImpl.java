package id.co.libralink.common.web.service.impl;

import id.co.libralink.common.base.code.ErrorCode;
import id.co.libralink.common.message.MessageSourceService;
import id.co.libralink.common.web.model.ErrorResponse;
import id.co.libralink.common.web.model.FieldErrorMessage;
import id.co.libralink.common.web.service.ErrorResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorResponseServiceImpl implements ErrorResponseService {

    private final MessageSourceService messageSourceService;

    @Override
    public FieldErrorMessage build(String field, ErrorCode code) {
        String message = messageSourceService.getLocalizedMessage(code.getKey());
        return new FieldErrorMessage(field, message);
    }

    @Override
    public ErrorResponse build(HttpStatus status, String defaultMessage) {
        return new ErrorResponse(status, defaultMessage);
    }

    @Override
    public ErrorResponse build(HttpStatus status, ErrorCode code) {
        String message = messageSourceService.getLocalizedMessage(code.getKey());
        return new ErrorResponse(status, code, message);
    }

    @Override
    public ErrorResponse build(HttpStatus status, ErrorCode code, Object[] args) {
        String message = messageSourceService.getLocalizedMessage(code.getKey(), args);
        return new ErrorResponse(status, code, message);
    }

    @Override
    public ErrorResponse build(HttpStatus status, ErrorCode code, String defaultMessage) {
        String message = messageSourceService.getLocalizedMessage(code.getKey(), defaultMessage);
        return new ErrorResponse(status, code, message);
    }

    @Override
    public ErrorResponse build(HttpStatus status, ErrorCode code, Object[] args, String defaultMessage) {
        String message = messageSourceService.getLocalizedMessage(code.getKey(), args, defaultMessage);
        return new ErrorResponse(status, code, message);
    }

}
